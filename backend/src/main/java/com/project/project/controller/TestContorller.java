package com.project.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestContorller {

    @RequestMapping(
            value = "/api/hello",
            method = RequestMethod.GET
    )
    public String hello() {
        return "Hello Spring";
    }
}
