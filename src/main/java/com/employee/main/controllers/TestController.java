package com.employee.main.controllers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Api
@ApiIgnore
public class TestController {

    @RequestMapping(value="/test")
    public String testPage() {
        return "test.html";
    }
}