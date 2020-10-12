package com.zuka.account.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    
    @Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String messageUser = this.messageSource.getMessage("invalid_message", null, LocaleContextHolder.getLocale());
		Problem problem = createProblemBuild(HttpStatus.BAD_REQUEST, ProblemType.USER_NOT_FOUND.getUri(),
				ProblemType.USER_NOT_FOUND.getTitle(), messageUser).build();
		return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
	}
		
    private Problem.ProblemBuilder createProblemBuild (HttpStatus status, String type, String title, String detail){
        return Problem.builder()
                .status(status.value())
                .type(type)
                .title(title)
                .details(detail);
    }
}
