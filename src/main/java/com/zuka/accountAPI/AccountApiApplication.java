package com.zuka.accountAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zuka.accountAPI.enums.Sex;
import com.zuka.accountAPI.model.Account;
import com.zuka.accountAPI.model.Adress;
import com.zuka.accountAPI.service.AccountService;

@SpringBootApplication
public class AccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApiApplication.class, args);
	}

}
