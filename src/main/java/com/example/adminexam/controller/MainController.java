package com.example.adminexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.adminexam.dto.UsersDTO;
import com.example.adminexam.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	/*
	 * 로그인은 post
	 */
	@PostMapping("/login")
	@ResponseBody
	public UsersDTO postLogin(@RequestBody UsersDTO usersDTO, HttpServletRequest request) {
		UsersDTO users = usersService.getLogin(usersDTO);
		HttpSession session = request.getSession(); //세션 불러오기
		/** 유저아이디와 유저이름 세션에 저장 (Map처럼 key와 value로 되어있음)*/
		session.setAttribute("userid", users.getUserId());
		session.setAttribute("username", users.getUserName()); 
		return users;
	}
	
	@GetMapping("/login-re")
	public String callLogin(){
		return "redirect:/emp"; // /emp controller 요청
	}
}
