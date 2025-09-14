package com.dawden.custom.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.dawden.custom.annotation.PositiveAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class PositiveAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE,
                "Processor is triggered"
        );
        for (Element element : roundEnv.getElementsAnnotatedWith(PositiveAnnotation.class)) {
            if (element.getKind() == ElementKind.FIELD) {
//                Convert Element to VariableElement(Field) since we know now that it is a FIELD
                VariableElement variableElement = (VariableElement) element;
//                Get FIELD value
                Object constantValue = variableElement.getConstantValue();
                if (constantValue instanceof Integer) {
                    int value = (int) constantValue;
                    if (value <= 0) {
                        processingEnv.getMessager().printMessage(
                                Diagnostic.Kind.ERROR,
                                "Field '" + variableElement.getSimpleName() + "' must be positive!",
                                element
                        );
                    } else {
                        processingEnv.getMessager().printMessage(
                                Diagnostic.Kind.NOTE,
                                "Field is positive"
                        );
                    }
                }
            }
        }
        return true;
    }
}
