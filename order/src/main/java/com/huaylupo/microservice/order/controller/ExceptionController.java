/**
 * 
 */
package com.huaylupo.microservice.order.controller;

import static java.util.Collections.singletonMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.huaylupo.microservice.order.model.ErrorMessage;
import com.huaylupo.microservice.order.model.ResponseWrapper;
import com.huaylupo.microservice.order.model.RestErrorList;

/**
 *
 * @author ihuaylupo
 * @version
 * @since Jun 28, 2018
 */

@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler {

    
	/**
	 * handleIOException - Handles all the Authentication Exceptions of the application. 
	 *@param request
	 *@param exception
	 *@return ResponseEntity<ResponseWrapper>
	 * @user ihuaylupo
	 * @since 2018-09-12 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper> handleIOException(HttpServletRequest request, Exception e){
    	
    	RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage(e.getLocalizedMessage(),e.getMessage(), e.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);
        
      
        return ResponseEntity.ok(responseWrapper);
	}	
	
}