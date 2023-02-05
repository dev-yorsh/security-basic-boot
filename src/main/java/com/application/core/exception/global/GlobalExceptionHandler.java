package com.application.core.exception.global;

import com.application.core.exception.http.BusinessException;
import com.application.core.exception.http.InternalException;
import com.application.core.exception.http.NotFoundException;
import com.application.core.util.GenericUtil;
import com.application.dto.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.application.core.constants.Constants.MessageUser.MSJ_INTERNAL_EXCEPTION;
import static com.application.core.constants.Constants.MessageUser.MSJ_REQUEST_ERROR;
import static com.application.core.constants.SecurityConstants.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*********** Global Handler ***********/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, MSJ_ERROR_SERVER, ex.getMessage(), ex);
    }

    /*********** InternalException Handler ***********/
    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Object> handleInternalException(InternalException ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), MSJ_INTERNAL_EXCEPTION, ex);
    }

    /*********** Authentication Handler ***********/
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return handleException(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), MSJ_ERROR_LOGIN, ex);
    }

    /*********** Authorization Handler ***********/
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return handleException(HttpStatus.FORBIDDEN, MSJ_ERROR_PRIVILEGE, ex.getMessage(), ex);
    }

    /*********** NotFoundException Handler ***********/
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex.getMessage(), MSJ_ERROR_NOT_FOUND, ex);
    }

    /*********** BusinessException Handler ***********/
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return handleException(HttpStatus.BAD_REQUEST, MSJ_REQUEST_ERROR, ex.getMessage(), ex);
    }

    /*********** Database Handler ***********/
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        return handleException(HttpStatus.SERVICE_UNAVAILABLE, MSJ_ERROR_DATABASE, ex.getMessage(), ex);
    }

    /*********** Deserializer Handler ***********/
    @Override
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, MSJ_ERROR_DESERIALIZE, ex.getLocalizedMessage(), ex);
    }

    /*********** Validation Handler ***********/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return handleExceptionWithDetailErrors(HttpStatus.BAD_REQUEST, MSJ_ERROR_BAD_REQUEST,
                MSJ_REQUEST_ERROR, errors);
    }

    private ResponseEntity<Object> handleException(HttpStatus status, String userMessage,
                                                   String technicalMessage, Throwable ex) {
        BaseErrorResponse response = new BaseErrorResponse(status.value(), userMessage, technicalMessage, GenericUtil.getCause(ex));
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<Object> handleExceptionWithDetailErrors(HttpStatus status, String userMessage,
                                                                   String technicalMessage, List<String> errors) {
        BaseErrorResponse response = new BaseErrorResponse(status.value(), userMessage, technicalMessage, errors);
        return ResponseEntity.status(status).body(response);
    }
}
