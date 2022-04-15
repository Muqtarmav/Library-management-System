package com.Library_Management_System.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/homeCont")
@RestController
public class HomeController {

    @GetMapping("/home")
    private String home(){
        return "this is home page";
    }


    @GetMapping("/admin")
    private String admin(){
        return "this is admin page";
    }


}
