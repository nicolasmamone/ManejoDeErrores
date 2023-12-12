package com.nico.manejodeerrores.exception;

import com.nico.manejodeerrores.dto.ErrorDetail;
import com.nico.manejodeerrores.dto.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//CONTROLADOR DE EXCEPTIONS

@ControllerAdvice
public class RestExceptionHandler {

    //Cuando se invoque a esta excepcion, este método lo va a manejar y hacer lo siguiente
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest httpServletRequest){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("El Recurso No Se Encontro");
        errorDetail.setDetail(exception.getClass().getName()); // obteniendo el nombre de la clase de la exception
        errorDetail.setDeveloperMessage(exception.getMessage());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        String requestPath = (String) httpServletRequest.getAttribute("javax.servlet.error.request_uri");
        if(requestPath==null){
            requestPath = httpServletRequest.getRequestURI();
        }
        errorDetail.setTitle("Validacion fallida");
        errorDetail.setDetail("La validacion de entrada fallo");
        errorDetail.setDeveloperMessage(exception.getMessage());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors) { // Para obtener los errores de cada campo
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fieldError.getField());

            if (validationErrorList == null) {
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fieldError.getField(),validationErrorList);
            }

            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetail,null,HttpStatus.BAD_REQUEST);
    }
}
