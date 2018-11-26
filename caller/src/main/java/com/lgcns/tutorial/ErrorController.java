package com.lgcns.tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {

	private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public List<FruitInfo> exception(final RuntimeException exception, HttpServletRequest req) {
		logger.error("Exception during execution of caller application", exception);

		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		
		String reqUrl = req.getRequestURL().toString();
		String[] split = reqUrl.split("/");
		String deadFruit = split[3].toUpperCase();
		
		FruitInfo fruitInfo = new FruitInfo("unknown", deadFruit, deadFruit + " IS DEAD" );
		fruitInfoList.add(fruitInfo);
		return fruitInfoList;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model) {
		logger.error("Exception during execution of caller application", throwable);
		
		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}
}
