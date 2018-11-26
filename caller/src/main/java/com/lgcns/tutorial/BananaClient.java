package com.lgcns.tutorial;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("BANANA")
public interface BananaClient {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	List<FruitInfo> callBanana();
	
	@RequestMapping(value = "/kiwi", method = RequestMethod.GET)
	List<FruitInfo> callBananaAndKiwi();
	
	@RequestMapping(value = "/cherry/banana/kiwi", method = RequestMethod.GET)
	List<FruitInfo> callBananaCherryBananaAndKiwi();
}
