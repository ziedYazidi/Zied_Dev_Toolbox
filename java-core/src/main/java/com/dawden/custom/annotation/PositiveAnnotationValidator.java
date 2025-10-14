package com.dawden.custom.annotation;

public class PositiveAnnotationValidator {

    @PositiveAnnotation
    private int age1 = -10;

    @PositiveAnnotation
    private int age2 = 10;
}
