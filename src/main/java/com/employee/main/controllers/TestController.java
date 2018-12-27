package com.employee.main.controllers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(hidden = true)
public class TestController {

    @RequestMapping(value="/test")
    public String testPage() {
        return "test.html";
    }
}