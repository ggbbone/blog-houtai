package com.yzg.blog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "demo")
public class DemoController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String demo() {
        return "helloWord";
    }
}
