package com.spring.practice.users.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practice.users.dao.UsersDao;
import com.spring.practice.users.dto.UsersDto;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao dao;
	
	@Override
	public Map<String, Object> isExistid(String inputId) {
		boolean isExist=dao.isExist(inputId);
		Map<String, Object> map=new HashMap<>();
		map.put("isExist", isExist);
		return map;
	}

	@Override
	public void addUser(UsersDto dto) {
		//dto 객체에 비밀번호를 암호화 해서 넣어준다.
		String inputPwd=dto.getPwd(); //회원가입 form 에 입력한 비밀번호
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPwd=encoder.encode(inputPwd); //암호화된 비밀번호
		//암호화된 비밀번호를 dto 객체에 다시 넣어준다. 
		dto.setPwd(encodedPwd);
		
		//dao  객체를 이용해서 새로운 사용자 정보를 DB 에 저장하기 
		dao.insert(dto);
	}

	@Override
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session) {
		boolean isValid=false;
		UsersDto resultDto=dao.getData(dto.getId());
		if(resultDto != null) {
			String encodedPwd=resultDto.getPwd();
			String inputPwd=dto.getPwd();
			isValid=BCrypt.checkpw(inputPwd, encodedPwd);
		}
		if(isValid) {
			session.setAttribute("id", dto.getId());
			mView.addObject("isSuccess", true);
		}else {
			mView.addObject("isSuccess", false);
		}
	}

}
