package com.lgcns.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KiwiController {

	private static Logger log = LoggerFactory.getLogger(KiwiApplication.class);
	
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
		return "I'm Kiwi!";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public List<FruitInfo> greet() {
		log.info("Access /greeting");

		List<FruitInfo> fruitInfoList = new ArrayList<FruitInfo>();
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();
		int randomNum = rand.nextInt(greetings.size());
		
		FruitInfo kiwiInfo = new FruitInfo(instanceIndex, "kiwi", greetings.get(randomNum) + ", I'm Kiwi!");
		fruitInfoList.add(kiwiInfo);
		return fruitInfoList;
	}
}
