package com.alexsei.itransition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/loginsocial")
    String getLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    String logout(){
        return "redirect:/";
    }
}
