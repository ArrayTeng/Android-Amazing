package com.amazing.tengfei.aroutercompiler;

import com.amazing.tengfei.aroutercompiler.utils.Constants;
import com.amazing.tengfei.aroutercompiler.utils.Log;
import com.amazing.tengfei.aroutercompiler.utils.Utils;
import com.amazing.tengfei.routerannotation.ARouter;
import com.amazing.tengfei.routerannotation.enums.RouteType;
import com.amazing.tengfei.routerannotation.model.RouteMeta;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

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
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 12:15 AM
 * email arrayadapter.cn@gmail.com
 * description
 */

@SupportedOptions(Constants.ARGUMENTS_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

    private Log log;

    private String moduleName;

    private Elements elementUtils;
    private Types typeUtils;
    private Filer filerUtils;

    private Map<String, List<RouteMeta>> groupMap = new HashMap<>();

    private Map<String, String> rootMap = new HashMap<>();

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(ARouter.class.getCanonicalName());
        return annotations;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        log = Log.newLog(processingEnv.getMessager());
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
        filerUtils = processingEnv.getFiler();

        Map<String, String> options = processingEnv.getOptions();
        if (!options.isEmpty()) {
            moduleName = options.get(Constants.ARGUMENTS_NAME);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (!Utils.isEmpty(annotations)) {

            //获取每一个被 ARouter 标记的节点信息
            Set<? extends Element> rootElements = roundEnv.getElementsAnnotatedWith(ARouter.class);
            if (!Utils.isEmpty(rootElements)) {

                processorRoute(rootElements);
            }
            return true;
        }
        return false;
    }

    private void processorRoute(Set<? extends Element> rootElements) {
        TypeElement activityTypeElement = elementUtils.getTypeElement("android.app.Activity");
        //遍历所有的节点信息

        for (Element element : rootElements) {
            RouteMeta routeMeta;
            TypeMirror elementMirror = element.asType();
            ARouter aRouter = element.getAnnotation(ARouter.class);
            //说明这个节点是Activity类型的
            if (typeUtils.isSubtype(elementMirror, activityTypeElement.asType())) {
                //创建节点信息
                routeMeta = new RouteMeta(RouteType.ACTIVITY, aRouter, element);
            } else {
                throw new RuntimeException("只支持Activity");
            }

            categories(routeMeta);

        }


        TypeElement routeGroupElement = elementUtils.getTypeElement(Constants.ELEMENT_ROUTE_GROUP);
        TypeElement routeRootElement = elementUtils.getTypeElement(Constants.ELEMENT_ROUTE_ROOT);
        generatedGroup(routeGroupElement);
        generatedRoot(routeGroupElement,routeRootElement);
    }

    private void generatedRoot(TypeElement routeGroupElement,TypeElement routeRootElement) {
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(routeGroupElement))));

        ParameterSpec spec = ParameterSpec.builder(parameterizedTypeName, "routes")
                .build();

        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("loadInto")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(spec);

        for (Map.Entry<String, String> entry : rootMap.entrySet()) {
            methodSpecBuilder.addStatement("routes.put($S, $T.class);", entry.getKey(), ClassName.get("com.amazing.tengfei.aroutercore",entry.getValue()));
        }

        String className = "ARouter$$Root$$" + moduleName;

        TypeSpec typeSpec = TypeSpec.classBuilder(className)
                .addMethod(methodSpecBuilder.build())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ClassName.get(routeRootElement))
                .build();

        JavaFile javaFile = JavaFile.builder("com.amazing.tengfei.aroutercore", typeSpec).build();

        try {
            javaFile.writeTo(filerUtils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatedGroup(TypeElement routeGroupElement) {

        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(RouteMeta.class));

        ParameterSpec spec = ParameterSpec.builder(parameterizedTypeName, "atlas")
                .build();

        for (Map.Entry<String, List<RouteMeta>> entry : groupMap.entrySet()) {

            MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("loadInto")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .addParameter(spec);

            //获取一个组里面所有的节点信息
            List<RouteMeta> routeMetas = groupMap.get(entry.getKey());

            for (RouteMeta routeMeta : routeMetas) {
                methodSpecBuilder.addStatement("atlas.put($S,$T.build($T.$L,$T.class,$S,$S))",
                        routeMeta.getPath(),
                        ClassName.get(RouteMeta.class),
                        ClassName.get(RouteType.class),
                        routeMeta.getRouteType(),
                        ClassName.get((TypeElement) routeMeta.getElement()),
                        routeMeta.getPath(),
                        routeMeta.getGroup());
            }


            //生成的类名
            String className = "ARouter$$Group$$" + entry.getKey();
            TypeSpec typeSpec = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(ClassName.get(routeGroupElement))
                    .addMethod(methodSpecBuilder.build())
                    .build();


            JavaFile javaFile = JavaFile.builder("com.amazing.tengfei.aroutercore", typeSpec)
                    .build();

            rootMap.put(entry.getKey(), className);
            try {
                javaFile.writeTo(filerUtils);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void categories(RouteMeta routeMeta) {
        if (routeVerify(routeMeta)) {
            List<RouteMeta> routeMetas = groupMap.get(routeMeta.getGroup());
            if (Utils.isEmpty(routeMetas)) {
                routeMetas = new ArrayList<>();
                routeMetas.add(routeMeta);
                groupMap.put(routeMeta.getGroup(), routeMetas);
            } else {
                routeMetas.add(routeMeta);
            }

        }
    }

    private boolean routeVerify(RouteMeta routeMeta) {
        String path = routeMeta.getPath();
        String group = routeMeta.getGroup();
        if (!path.startsWith("/")) {
            return false;
        }

        if (group == null || group.isEmpty()) {
            String defaultGroup = path.substring(1, path.indexOf("/", 1));
            if (Utils.isEmpty(defaultGroup)) {
                return false;
            }
            routeMeta.setGroup(defaultGroup);
        }
        return true;
    }
}
