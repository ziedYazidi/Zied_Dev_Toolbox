package com.zied.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @ApiOperation(value = "sayHello", notes = "say hello", nickname = "sayHello")
    @GetMapping(value = "/api/javainuse")
    public String sayHello() {
        return "Hello World";
    }
}
