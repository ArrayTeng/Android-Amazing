package com.example.compiler;

import com.example.annotations.OnClick;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * @author tengfei
 * date 2019/6/5 10:34 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ClickViewField {

    private ExecutableElement executableElement;
    private int resId;
    private String methodName;

    public ClickViewField(Element executableElement) {
        this.executableElement = (ExecutableElement) executableElement;
        OnClick onClick = executableElement.getAnnotation(OnClick.class);
        resId = onClick.value();
        methodName = executableElement.getSimpleName().toString();
    }

    public int getResId() {
        return resId;
    }

    public String getMethodName() {
        return methodName;
    }

    public ExecutableElement getExecutableElement() {
        return executableElement;
    }
}
