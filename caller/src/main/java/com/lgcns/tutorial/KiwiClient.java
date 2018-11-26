package com.lgcns.tutorial;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("KIWI")
public interface KiwiClient {
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	List<FruitInfo> callKiwi();
}
