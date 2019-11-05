package com.example.poet;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

/**
 * @author tengfei
 */
public class JavaPoetTest {

    public static void main(String[] args) {

        ClassName bundleClass = ClassName.get("android.os", "Bundle");
        ParameterSpec parameterSpec = ParameterSpec.builder(bundleClass, "savedInstanceState")
                .addAnnotation(ClassName.get("android.support.annotation","Nullable"))
                .build();

        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("onCreate")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PROTECTED)
                .returns(void.class)
                .addParameter(parameterSpec);

        MethodSpec constructorMethod = MethodSpec.constructorBuilder()
                .addParameter(String.class,"name")
                .build();

        methodSpecBuilder.addStatement("super.onCreate(savedInstanceState);");
        methodSpecBuilder.addStatement("setContentView(R.layout.activity_main);");

        TypeSpec activitySpec = TypeSpec.classBuilder("JavaPoetActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ClassName.get("android.support.v7.app", "AppCompatActivity"))
                .addSuperinterface(ClassName.get("com.example.poet","IPoet"))
                .addMethod(methodSpecBuilder.build())
                .addMethod(constructorMethod)
                .addField(FieldSpec.
                        builder(ClassName.get("java.lang","String"),"name",Modifier.PUBLIC)
                        .initializer("tengfei")
                        .build())
                .addAnnotation(Deprecated.class)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.javapoet",activitySpec).build();

        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
