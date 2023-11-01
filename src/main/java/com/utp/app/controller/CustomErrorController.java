package com.utp.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{

	@RequestMapping("/error")
	public String errorHandler(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        String errorMessage = "";
	        
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	errorMessage = "Ruta no encontrada!";
	        } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	errorMessage = "Error de servidor!, ten paciencia lo estamos solucionando.";
	        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
	        	errorMessage = "Tu usuario no tiene los permisos para acceder a esta ruta.";
	        }
	        
	        model.addAttribute("error", statusCode);
	        model.addAttribute("errorMessage", errorMessage);
	    }
		return "error";
	}
	
}
