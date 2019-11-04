package com.example.routercompiler.processor;

import com.example.routerannotation.Extra;
import com.example.routerannotation.Route;
import com.example.routerannotation.entity.RouteMeta;
import com.example.routercompiler.utils.Consts;
import com.example.routercompiler.utils.Log;
import com.example.routercompiler.utils.Utils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


/**
 * @author tengfei
 * date 2019-10-31 23:29
 * email tengfeigo@outlook.com
 * description
 */

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

    /**
     * 文件生成器
     */
    private Filer filerUtils;

    /**
     * 类信息工具类
     */
    private Types typesUtils;

    /**
     * 节点工具类
     */
    private Elements elementUtils;

    private Map<String, List<RouteMeta>> groupMap = new HashMap<>();
    private Log log;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        log = Log.newLog(processingEnvironment.getMessager());
        filerUtils = processingEnvironment.getFiler();
        typesUtils = processingEnvironment.getTypeUtils();
        elementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Extra.class.getCanonicalName());
        types.add(Route.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!Utils.isEmpty(set)) {
            //获取所有被注解 Route 标记的元素
            Set<? extends Element> elementsWithRouters = roundEnvironment.getElementsAnnotatedWith(Route.class);
            if (!Utils.isEmpty(elementsWithRouters)) {
                //处理注解集合
                parseRouterElement(elementsWithRouters);
            }
            return true;
        }

        return false;
    }

    /**
     * @param elementsWithRouters 获取所有被注解 Route 标记的元素
     */
    private void parseRouterElement(Set<? extends Element> elementsWithRouters) {

        TypeElement activityTypeElement = elementUtils.getTypeElement(Consts.ACTIVITY);
        TypeMirror activityMirror = activityTypeElement.asType();

        RouteMeta routeMeta = null;

        //遍历获取每一个元素
        for (Element routerElement : elementsWithRouters) {
            //获取每一个节点元素信息
            TypeMirror routerMirror = routerElement.asType();
            //如果注解是定义在 activity 上的，
            if (typesUtils.isSubtype(routerMirror, activityMirror)) {
                Route route = routerElement.getAnnotation(Route.class);
                //保存元素信息
                routeMeta = new RouteMeta(RouteMeta.Type.ACTIVITY, route, routerElement);
            } else {
                throw new RuntimeException("Route 注解必须定义在Activity 上" + routerElement);
            }

            categories(routeMeta);

        }


        TypeElement iRouteGroupElement = elementUtils.getTypeElement(Consts.IROUTE_GROUP);
        generatedGroup(iRouteGroupElement);
    }

    private void generatedGroup(TypeElement iRouteGroupElement) {

        //创建参数类型
        ParameterizedTypeName typeName = ParameterizedTypeName.get(ClassName.get(Map.class),
                ClassName.get(String.class), ClassName.get(RouteMeta.class));

        //创建参数名称
        ParameterSpec parameterSpec = ParameterSpec.builder(typeName, "atlas").build();

        for (Map.Entry<String, List<RouteMeta>> stringListEntry : groupMap.entrySet()) {

            //构建方法
            MethodSpec.Builder loadIntoMethodBuilder = MethodSpec.methodBuilder("loadInto")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .addAnnotation(Override.class)
                    .addParameter(parameterSpec);

            String groupName = stringListEntry.getKey();
            List<RouteMeta> routeMetaList = stringListEntry.getValue();

            for (RouteMeta routeMeta : routeMetaList) {
                //atlas.put("/main/test", RouteMeta.build(RouteMeta.Type.ACTIVITY,SecondActivity.class, "/main/test", "main"));
                // javaPoet 的占位符信息 http://www.27house.cn/archives/1429
                loadIntoMethodBuilder.addStatement("atlas.put($S,$T.build($T.$L,$T.class,$S,$S))",
                        routeMeta.getPath(),
                        ClassName.get(RouteMeta.class),
                        ClassName.get(RouteMeta.Type.class),
                        routeMeta.getType(),
                        ClassName.get((TypeElement) routeMeta.getElement()),
                        routeMeta.getPath().toLowerCase(),
                        routeMeta.getGroup().toLowerCase());
            }

            String groupClassName = Consts.NAME_OF_GROUP+groupName;
            //创建java类
            TypeSpec typeSpec = TypeSpec.classBuilder(groupClassName)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                    .addSuperinterface(ClassName.get(iRouteGroupElement))
                    .addMethod(loadIntoMethodBuilder.build())
                    .build();
            try {
                JavaFile.builder(Consts.PACKAGE_OF_GENERATE_FILE,typeSpec).build().writeTo(filerUtils);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void categories(RouteMeta routeMeta) {
        if (routeVerify(routeMeta)) {
            List<RouteMeta> routeMetas = groupMap.get(routeMeta.getGroup());
            if (Utils.isEmpty(routeMetas)) {
                String group = routeMeta.getGroup();
                List<RouteMeta> routeMetaList = new ArrayList<>();
                routeMetaList.add(routeMeta);
                groupMap.put(group, routeMetaList);
            } else {
                routeMetas.add(routeMeta);
            }
        } else {
            log.i("路由信息不符合规格");
        }

    }

    /**
     * 检查该路由信息是否符合规格
     */
    private boolean routeVerify(RouteMeta routeMeta) {
        String path = routeMeta.getPath();
        String group = routeMeta.getGroup();
        if (Utils.isEmpty(path) || !path.startsWith("/")) {
            //从path中将group裁剪出来
            return false;
        }
        if (Utils.isEmpty(group)) {
            String subStringGroup = path.substring(1, path.indexOf("/", 1));
            if (Utils.isEmpty(subStringGroup)) {
                return false;
            }
            routeMeta.setGroup(subStringGroup);
            return true;
        }
        return false;
    }
}
