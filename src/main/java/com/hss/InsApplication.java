package com.hss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hss.modules.dao.UserDao")
public class InsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsApplication.class, args);
	}
}
