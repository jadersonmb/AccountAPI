package com.zuka.account.exception;

public class AccountException extends RuntimeException {

    public AccountException(){
        super();
    }

    public AccountException(String message, ProblemType problemType){
        super(message);
    }
}
