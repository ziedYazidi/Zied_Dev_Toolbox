package com.dawden.router;

import com.dawden.service.HelloWorldService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldCamelRouter extends RouteBuilder {

    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{myPeriod}}")
                .routeId("hello")
                .bean(helloWorldService, "saySomething")
                .to("stream:out");
    }
}
