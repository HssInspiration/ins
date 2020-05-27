package com.hss;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hss.dao")
public class InsApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsApplication.class, args);
	}
}
