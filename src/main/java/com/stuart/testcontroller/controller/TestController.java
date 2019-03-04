package com.stuart.testcontroller.controller;

import com.stuart.testcontroller.model.Dog;
import com.stuart.testcontroller.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@ConditionalOnProperty(name="test.controller.enabled", havingValue="true")
public class TestController {

    @Autowired
    DogService dogService;

    @RequestMapping(value="/test")
    public String getTest(){
        dogService.findDogs("001", 7, true);
        dogService.findDogs("", 11, null);
        return "test";
    }

}
//
