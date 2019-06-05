package com.example.compiler;

import com.example.annotations.BindView;
import com.example.annotations.OnClick;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;


@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;
    private Messager mMessager;

    /**
     * map 中保存的是外层元素和一个 AnnotatedClass 一个外层元素对应着一个 AnnotatedClass
     */
    private Map<String, AnnotatedClass> mAnnotatedClassMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        //filter用来创建新的源文件、class文件以及辅助文件
        mFiler = processingEnvironment.getFiler();
        //elements中包含着操作element的工具方法
        mElementUtils = processingEnvironment.getElementUtils();
        //用来报告错误、警告以及其他提示信息
        mMessager = processingEnvironment.getMessager();
        mAnnotatedClassMap = new TreeMap<>();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mAnnotatedClassMap.clear();
        //增加View绑定
        processBindView(roundEnvironment);

        for (AnnotatedClass annotatedClass:mAnnotatedClassMap.values()){
            try {
                annotatedClass.generateFile().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void processBindView(RoundEnvironment roundEnvironment) {
        //遍历获取到所有被 BindView 标记的元素，实际上是遍历项目中所有被 BindView 标记的元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(BindView.class)) {
            //获取到当前元素所对应的 AnnotatedClass
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            //把当前被 BindView 标记的元素封装到 BindViewField 中
            BindViewField bindViewField = new BindViewField(element);
            annotatedClass.addBindViewField(bindViewField);
        }
    }


    private AnnotatedClass getAnnotatedClass(Element element) {
        //获取外层元素
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        //外层元素名，这里指 Activity
        String enclosingClassName = enclosingElement.getQualifiedName().toString();
        //第一次获取的时候肯定是 null
        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(enclosingClassName);
        if (annotatedClass == null){
            //在 AnnotatedClass 中传入了外层元素和 Elements 工具
            annotatedClass = new AnnotatedClass(enclosingElement,mElementUtils);
            //将 annotatedClass 保存在 map 中
            mAnnotatedClassMap.put(enclosingClassName,annotatedClass);
        }
        return annotatedClass;
    }
}
