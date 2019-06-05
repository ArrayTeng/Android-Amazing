package com.example.compiler;

import com.example.annotations.BindView;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * @author tengfei
 * date 2019/6/5 7:54 PM
 * email tengfeigo@outlook.com
 * description
 */
public class BindViewField {

    private VariableElement variableElement;
    private int mResId;

    BindViewField(Element element) {
        variableElement = (VariableElement) element;
        //获取标记在它上面的 BindView 注解
        BindView bindView = variableElement.getAnnotation(BindView.class);
        //根据注解可以获取到id值
        mResId = bindView.value();
    }

    Name getFieldName() {
        return variableElement.getSimpleName();
    }

    public int getmResId() {
        return mResId;
    }

    /**
     *
     * @return 变量类型
     */
    TypeMirror getFieldType() {
        return variableElement.asType();
    }
}
