package com.a20da10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/try")
public class TryfirstRuiController {


}
