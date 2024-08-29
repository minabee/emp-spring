package com.example.adminexam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.adminexam.dto.UsersDTO;
@Mapper
public interface UsersMapper {
	UsersDTO getUsers(@RequestBody UsersDTO usersDTO);
}
