package com.example.adminexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DeptController {

    private static final String VIEW_PREFIX = "dept/";

    /** 사원페이지 조회*/
    @GetMapping("/dept")
    public String loadMyinfo(ModelMap model, HttpServletRequest request) throws Exception{
		
    	model.addAttribute("msg","hello world");	
        return VIEW_PREFIX+"dept";
    }
}
