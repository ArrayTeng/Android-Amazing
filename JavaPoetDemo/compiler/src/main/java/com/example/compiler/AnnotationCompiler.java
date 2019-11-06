package com.example.compiler;

import com.example.annotation.ContentView;
import com.example.compiler.utils.Log;
import com.example.compiler.utils.Utils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
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
import javax.lang.model.util.Types;

/**
 * @author tengfei
 * date 2019-11-06 21:54
 * email tengfeigo@outlook.com
 * description
 */

@AutoService(Processor.class)
public class AnnotationCompiler extends AbstractProcessor {

    private static final String TAG = "AnnotationCompiler_TAG";

    private Filer filerUtils;

    private Elements elementsUtils;

    private Types typesUtils;

    private Log log;


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationSet = new LinkedHashSet<>();
        annotationSet.add(ContentView.class.getCanonicalName());
        return annotationSet;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        log = Log.newLog(processingEnvironment.getMessager());
        filerUtils = processingEnvironment.getFiler();
        elementsUtils = processingEnvironment.getElementUtils();
        typesUtils = processingEnvironment.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!Utils.isEmpty(set)) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ContentView.class);
            if (!Utils.isEmpty(elements)) {
                procese(elements);
            }

        }
        return true;
    }

    private void procese(Set<? extends Element> elements) {
        for (Element element : elements) {
            int id = element.getAnnotation(ContentView.class).id();
            String className = element.getSimpleName().toString();
            MethodSpec methodSpec = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get("android.app", "Activity"), "activity")
                    .addStatement("activity.setContentView($L);", id)
                    .build();

            TypeSpec typeSpec = TypeSpec.classBuilder(className + "_ViewBind")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodSpec)
                    .build();


            try {
                JavaFile.builder("com.example.javapoet", typeSpec).build().writeTo(filerUtils);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
