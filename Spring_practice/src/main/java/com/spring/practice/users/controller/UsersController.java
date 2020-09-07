package com.spring.practice.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practice.users.dto.UsersDto;
import com.spring.practice.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId) {
		return service.isExistid(inputId);
	}
	
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		String url=request.getParameter("url");
		if(url==null) {
			String cpath=request.getContextPath();
			url=cpath+"home.do";
		}
		request.setAttribute("url", url);
		return "users/loginform";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
		String url=request.getParameter("url");
		String encodedUrl=URLEncoder.encode(url);
		mView.addObject("url",url);
		mView.addObject("encodedUrl", encodedUrl);
		
		service.loginProcess(dto, mView, session);
		mView.setViewName("users/login");
		return mView;
	}
	
}
