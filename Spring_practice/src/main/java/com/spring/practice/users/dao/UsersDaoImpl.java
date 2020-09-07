package com.spring.practice.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.practice.users.dto.UsersDto;
@Repository
public class UsersDaoImpl implements UsersDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public boolean isExist(String inputId) {
		String id=session.selectOne("users.isExist",inputId);
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert",dto);
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}

}
