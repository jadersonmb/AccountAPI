package com.zuka.account.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public enum ProblemType {


    USER_NOT_FOUND("/user-not-found", "User not found");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://zuka.com.br" + path;
        this.title = title;
    }
}
