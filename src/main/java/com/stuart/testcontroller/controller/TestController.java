package com.stuart.testcontroller.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name="test.controller.enabled", havingValue="true")
public class TestController {

    @RequestMapping(value="/test")
    public String getTest(){
        return "test";
    }
}
