package com.spring.practice.file.dao;

import java.util.List;

import com.spring.practice.file.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int getCount(FileDto dto);
}
