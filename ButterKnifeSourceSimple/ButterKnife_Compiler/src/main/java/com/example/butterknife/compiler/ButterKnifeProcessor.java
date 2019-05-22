package com.example.butterknife.compiler;

import com.example.butterknife.annotations.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import javax.lang.model.util.Elements;

/**
 * @author tengfei
 * date 2019/5/15 2:22 PM
 * email tengfeigo@outlook.com
 * description
/**
 * 自定义注解处理器 -- APT 只是生成代码的一个工具，继承 AbstractProcessor ，点击运行的时候会访问到继承自 AbstractProcessor 的类，
 * 在 ButterKnifeProcessor 中定义要生成的代码
 * <p>
 * AutoService协助开发人员注册注解处理器
 * AutoService注解处理器是Google开发的，
 * 用来生成 META-INF/services/javax.annotation.processing.Processor 文件的，
 * 你只需要在你定义的注解处理器上添加 @AutoService(Processor.class) 就可以了
 */
@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    /**
     * 指定支持的 java 版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 指定支持的注解类型
     * @return 返回支持的注解类型的 set 集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        //需要解析的自定义注解
        annotations.add(BindView.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //获取所有被 BindView 注解标记的元素
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        Map<Element, List<Element>> elementMap = new LinkedHashMap<>();

        for (Element element : elements) {
            //获取元素的外层元素
            Element enclosingElement = element.getEnclosingElement();
            List<Element> viewBindElements = elementMap.get(enclosingElement);
            if (viewBindElements == null) {
                viewBindElements = new ArrayList<>();
                elementMap.put(enclosingElement, viewBindElements);
            }
            viewBindElements.add(element);
        }

        for (Map.Entry<Element, List<Element>> entry : elementMap.entrySet()) {
            Element enclosingElement = entry.getKey();
            List<Element> viewBindElements = entry.getValue();
            //使用JavaPoet生成类
            String activityClassNameStr = enclosingElement.getSimpleName().toString();
            ClassName unBinderClassName = ClassName.get("com.example.butterknife.simple", "Unbinder");
            ClassName activityClassName = ClassName.bestGuess(activityClassNameStr);
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityClassName + "_ViewBinding")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addSuperinterface(unBinderClassName)
                    .addField(activityClassName, "target", Modifier.PRIVATE);

            //构建方法
            MethodSpec.Builder unBinderMethodBuilder = MethodSpec.methodBuilder("unBinder")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC);
            unBinderMethodBuilder.addStatement("$T target = this.target", activityClassName);
            unBinderMethodBuilder.addStatement("if (target == null) throw new IllegalStateException(\"Bindings already cleared.\");");

            //构建构造函数
            MethodSpec.Builder constructMethodBuilder = MethodSpec.constructorBuilder()
                    .addParameter(activityClassName, "target")
                    .addStatement("this.target = target")
                    .addModifiers(Modifier.PUBLIC);

            // findViewById 功能的实现
            for (Element viewBindElement : viewBindElements) {
                String fieldName = viewBindElement.getSimpleName().toString();
                ClassName utilsClassName = ClassName.get("com.example.butterknife.simple","Utils");
                int resId = viewBindElement.getAnnotation(BindView.class).value();
                constructMethodBuilder.addStatement("target.$L = $T.findViewById(target,$L)",fieldName,utilsClassName,resId);
                unBinderMethodBuilder.addStatement("target.$L = null",fieldName);
            }

            //将构建的方法和构造方法添加到类中
            classBuilder.addMethod(constructMethodBuilder.build());
            classBuilder.addMethod(unBinderMethodBuilder.build());

            try {
                //获取对应的包名
                String packageName = mElementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();
                JavaFile.builder(packageName, classBuilder.build())
                        .addFileComment("ButterKnife自动生成")
                        .build()
                        .writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
