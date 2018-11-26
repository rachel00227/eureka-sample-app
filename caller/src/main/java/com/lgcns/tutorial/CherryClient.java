package com.lgcns.tutorial;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("CHERRY")
public interface CherryClient {
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	List<FruitInfo> callCherry();
	
	@RequestMapping(value = "/banana", method = RequestMethod.GET)
	List<FruitInfo> callCherryAndBanana();
	
	@RequestMapping(value = "/banana/kiwi", method = RequestMethod.GET)
	List<FruitInfo> callCherryBananaAndKiwi();
}
