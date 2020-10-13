package com.zuka.account.exception;

import lombok.Getter;

@Getter
public enum ProblemType {


	INVALID_BODY("/invaled-body", "Invaled Body", "invalid_message_body"),
	INVALID_BODY_PARAM("/invaled-body", "Invaled Body", "invalid_message_body_param"),
	INVALID_VALUE_LONG_DATABASE("/invaled-long-database", "Value Long", "invalid_value_long_database"),
    USER_NOT_FOUND("/user-not-found", "User not found", "account_not_exists"),
    USER_NOT_EXISTS("/user-not-exists", "User not exists", "user_not_found"),
	CPF_ALREADY_EXISTS("/cpf-exists", "CPF already exists", "cpf_already_exists"), 
	CELL_PHONE_ALREADY_EXISTS("/cellphone-exists", "CellPhone already exists", "cellphone_already_exists");

    private String uri;
    private String title;
    private String messageSource;

    ProblemType(String path, String title, String messageSource){
        this.uri = "https://zuka.com.br" + path;
        this.title = title;
        this.messageSource = messageSource;
    }
}
