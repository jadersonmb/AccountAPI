package com.zuka.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String type;
	private String title;
	private String details;

	private String userMessage;
	private String uri;

	public AccountException() {
		super();
	}
	
	public AccountException(String message) {
		super(message);
	}

	public AccountException(Integer status, String type, String title, String details) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.details = details;
	}
}
