package com.example.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author tengfei
 * date 2019/6/5 4:12 PM
 * email tengfeigo@outlook.com
 * description
 */
public class AnnotatedClass {

    private TypeElement typeElement;
    private Elements elements;

    private List<BindViewField> bindViewFieldList;
    private List<ClickViewField> clickViewFieldList;

    /**
     * @param typeElement 外层元素，指代 Activity
     * @param elements
     */
    public AnnotatedClass(TypeElement typeElement, Elements elements) {
        this.typeElement = typeElement;
        this.elements = elements;
        bindViewFieldList = new ArrayList<>();
        clickViewFieldList = new ArrayList<>();
    }

    public void addBindViewField(BindViewField bindViewField) {
        bindViewFieldList.add(bindViewField);
    }

    public void addClickViewField(ClickViewField clickViewField) {
        clickViewFieldList.add(clickViewField);
    }

    /**
     * 构建代码
     */
    JavaFile generateFile() {
        ClassName utilsClassName = ClassName.get("com.example.butterknife", "Utils");
        ClassName viewClassName = ClassName.get("android.view","View");
        MethodSpec.Builder constructMethodBuilder = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(typeElement.asType()), "target",Modifier.FINAL)
                .addStatement("this.target = target")
                .addModifiers(Modifier.PUBLIC);

        for (BindViewField bindViewField : bindViewFieldList) {
            //构建 findViewById

            constructMethodBuilder.addStatement("target.$L = $T.findViewById(target,$L)", bindViewField.getFieldName(), utilsClassName, bindViewField.getmResId());
        }

        if (clickViewFieldList != null) {
            for (ClickViewField clickViewField : clickViewFieldList) {
                constructMethodBuilder.addStatement("$T.findViewById(target,$L).setOnClickListener(new $T.OnClickListener()" +
                        " {" +
                        "@Override " +
                        "public void onClick($T view) " +
                        "{ " +
                        "target.$N " +
                        "}" +
                        "}" +
                        ");",utilsClassName,clickViewField.getResId(),viewClassName,viewClassName,clickViewField.getMethodName()+"();");
            }
        }

        //构建 unBindView 方法
        MethodSpec.Builder unBindViewMethod = MethodSpec.methodBuilder("unBindView")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC);

        for (BindViewField bindViewField : bindViewFieldList) {
            unBindViewMethod.addStatement("target.$L = null", bindViewField.getFieldName());
        }

        ClassName viewBinderClass = ClassName.get("com.example.butterknife", "ViewBinder");

        //开始构建类
        TypeSpec injectClass = TypeSpec.classBuilder(typeElement.getSimpleName() + "$$ViewBinder")
                .addModifiers(Modifier.PUBLIC)
                .addField(TypeName.get(typeElement.asType()), "target")
                .addSuperinterface(viewBinderClass)
                .addMethod(constructMethodBuilder.build())
                .addMethod(unBindViewMethod.build())
                .build();
        String packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();
        return JavaFile.builder(packageName, injectClass).build();

    }

}
