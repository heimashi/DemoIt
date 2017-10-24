package com.sw.annotation.compiler;

import com.sw.annotation.clazz.BindViewClazz;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;


public class BindViewField {
    private VariableElement mFieldElement;
    private int mResId;

    public BindViewField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(
                String.format("Only fields can be annotated with @%s", BindViewClazz.class.getSimpleName()));
        }

        mFieldElement = (VariableElement) element;
        BindViewClazz bindView = mFieldElement.getAnnotation(BindViewClazz.class);
        mResId = bindView.value();

        if (mResId < 0) {
            throw new IllegalArgumentException(
                String.format("value() in %s for field %s is not valid !", BindViewClazz.class.getSimpleName(),
                    mFieldElement.getSimpleName()));
        }
    }

    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }

    public int getResId() {
        return mResId;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }
}
