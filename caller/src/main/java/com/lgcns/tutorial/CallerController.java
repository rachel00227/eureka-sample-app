package com.lgcns.tutorial;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CallerController {
	
	private static Logger log = LoggerFactory.getLogger(CallerApplication.class);

	@Autowired
	CherryClient cherryClient;
	
	@Autowired
	BananaClient bananaClient;
	
	@Autowired
	KiwiClient kiwiClient;
	
	@RequestMapping("/")
	public String index(Model model) {
		log.info("Access /");
		return "index";
	}
	
	@RequestMapping(value = "/cherry", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callCherry(){
		return cherryClient.callCherry();
	}
	
	@RequestMapping(value = "/kiwi", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callKiwi(){
		return kiwiClient.callKiwi();
	}
	
	@RequestMapping(value = "/banana", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callBanana(){
		return bananaClient.callBanana();
	}
	
	@RequestMapping(value = "/cherry/banana", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callCherryAndBanana(){
		return cherryClient.callCherryAndBanana();
	}
	
	@RequestMapping(value = "/banana/kiwi", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callBananaAndKiwi(){
		return bananaClient.callBananaAndKiwi();
	}
	
	@RequestMapping(value = "/cherry/banana/kiwi", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callCherryBananaAndKiwi(){
		return cherryClient.callCherryBananaAndKiwi();
	}
	
	@RequestMapping(value = "/banana/cherry/banana/kiwi", method = RequestMethod.GET)
	public @ResponseBody List<FruitInfo> callBananaCherryBananaAndKiwi(){
		return bananaClient.callBananaCherryBananaAndKiwi();
	}
}
