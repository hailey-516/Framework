package com.kh.lclinic.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.lclinic.model.vo.User;

@Mapper
public interface UserMapper {

	User loginUser(String id, String password);
	
}
