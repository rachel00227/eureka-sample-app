package com.lgcns.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClients({ 
	  @RibbonClient(name = "BANANA", configuration = RibbonConfiguration.class),
	  @RibbonClient(name = "KIWI", configuration = RibbonConfiguration.class)
}) 
public class CherryController {

	private static Logger log = LoggerFactory.getLogger(CherryApplication.class);
	
//	@Value("${server.port}")
//	  String instanceIndex;
	
	@Value("${instance.index}")
	String instanceIndex;
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/")
	public String home() {
		log.info("Access /");
		return "I'm cherry!";
	}
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public List<FruitInfo> greet() {
		log.info("Access /greeting");
		
		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();
		int randomNum = rand.nextInt(greetings.size());
		
		FruitInfo cherryInfo = new FruitInfo(instanceIndex, "cherry", greetings.get(randomNum) + ", I'm Cherry!");
		fruitInfoList.add(cherryInfo);
		return fruitInfoList;
	}
	
	@RequestMapping(value = "/banana", method = RequestMethod.GET)
	public List<FruitInfo> callBanana() {
		log.info("Access /banana");
		
		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		String url = "http://BANANA/greeting";	
		
		ResponseEntity<FruitInfo[]> responseEntity = this.restTemplate.getForEntity(url, FruitInfo[].class);
		FruitInfo[] fruitInfos = responseEntity.getBody();
		FruitInfo cherryInfo = new FruitInfo(instanceIndex, "cherry", "I'm Cherry!");
		
		fruitInfoList.add(cherryInfo);
		for (FruitInfo fruitInfo : fruitInfos) {
			fruitInfoList.add(fruitInfo);
		}
		return fruitInfoList;
	}
	
	@RequestMapping(value = "/banana/kiwi", method = RequestMethod.GET)
	public List<FruitInfo> callBananaAndKiwi() {
		log.info("Access /banana/kiwi");
		
		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		String url = "http://BANANA/kiwi";
		
		ResponseEntity<FruitInfo[]> responseEntity = this.restTemplate.getForEntity(url, FruitInfo[].class);
		FruitInfo[] fruitInfos = responseEntity.getBody();
		FruitInfo cherryInfo = new FruitInfo(instanceIndex, "cherry", "I'm Cherry!");
		
		fruitInfoList.add(cherryInfo);
		for (FruitInfo fruitInfo : fruitInfos) {
			fruitInfoList.add(fruitInfo);
		}
		return fruitInfoList;
	}
	
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public List<FruitInfo> exception(final RuntimeException exception, HttpServletRequest req) {
		log.error("Exception during execution of cherry application", exception);

		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		String reqUrl = req.getRequestURL().toString();
		String[] split = reqUrl.split("/");
		String deadFruit = split[3].toUpperCase();
		
		FruitInfo fruitInfo = new FruitInfo("unknown", deadFruit, deadFruit + " IS DEAD" );
		FruitInfo cherryInfo = new FruitInfo(instanceIndex, "cherry", "I'm Cherry!");
		fruitInfoList.add(cherryInfo);
		fruitInfoList.add(fruitInfo);
		return fruitInfoList;
	}
}
