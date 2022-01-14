package com.ld.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class MainController {

    @GetMapping(path = "/api")
    public String mainPage() {
        return "main";
    }
}
