package com.example.adminexam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adminexam.dto.UsersDTO;
import com.example.adminexam.mapper.UsersMapper;

@Service
public class UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	public UsersDTO getLogin(UsersDTO usersdto) {
		return usersMapper.getUsers(usersdto);
	}

}
