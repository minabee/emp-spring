package com.example.adminexam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value= {"com.example.adminexam.mapper"})
public class AdminexamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminexamApplication.class, args);
	}

}

