package com.example.routercompiler.processor;

import com.example.routerannotation.Extra;
import com.example.routerannotation.Route;
import com.example.routerannotation.entity.RouteMeta;
import com.example.routercompiler.utils.Consts;
import com.example.routercompiler.utils.Utils;
import com.google.auto.service.AutoService;

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


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

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
    }

    private void categories(RouteMeta routeMeta) {
        if (routeVerify(routeMeta)) {
            List<RouteMeta> routeMetas = groupMap.get(routeMeta.getGroup());
            if (Utils.isEmpty(routeMetas)){
                String group = routeMeta.getGroup();
                List<RouteMeta> routeMetaList = new ArrayList<>();
                routeMetaList.add(routeMeta);
                groupMap.put(group,routeMetaList);
            }else {
                routeMetas.add(routeMeta);
            }
        } else {

        }

    }

    private boolean routeVerify(RouteMeta routeMeta) {
        return false;
    }
}
