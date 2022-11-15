package com.rvg.operationmanagement.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvg.operationmanagement.api.model.ApiErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Calendar;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UnknownOperationException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        apiErrorDTO.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
        apiErrorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        apiErrorDTO.setError("Unknown operation");
        apiErrorDTO.setPath(request.getContextPath());
        apiErrorDTO.setMessage(ex.getMessage());

        String bodyOfResponse = "";
        try {
            bodyOfResponse = new ObjectMapper().writeValueAsString(apiErrorDTO);
        } catch (Exception e) {
            log.info("Error while parsing exception to JSON", e);
        }
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
