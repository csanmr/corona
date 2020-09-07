package com.spring.practice.users.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.spring.practice.users.dto.UsersDto;

public interface UsersService {
	public Map<String, Object> isExistid(String inputId);
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session);
}
