package com.spring.practice.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.practice.file.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao {
	@Autowired
	private SqlSession session;

	@Override
	public List<FileDto> getList(FileDto dto) {
		List<FileDto> list=session.selectList("file.getList", dto);
		return list;
	}

	@Override
	public int getCount(FileDto dto) {
		return session.selectOne("file.getCount", dto);
	}
	
}
