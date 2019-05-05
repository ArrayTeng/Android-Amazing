package com.example.butterknife.compiler;

import com.example.butterknife.annotations.BindView;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * APT 只是生成代码的一个工具，继承 AbstractProcessor ，点击运行的时候会访问到继承自 AbstractProcessor 的类，
 * 在 ButterKnifeProcessor 中定义要生成的代码
 */
@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {
    /**
     * 指定处理的版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 给到需要处理的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        return super.getSupportedAnnotationTypes();
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        //需要解析的自定义注解
        annotations.add(BindView.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
