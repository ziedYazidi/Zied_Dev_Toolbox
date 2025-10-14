package com.dawden.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    private int counter;

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return String.format("%s I am invoked %d times", say, ++counter);
    }
}
