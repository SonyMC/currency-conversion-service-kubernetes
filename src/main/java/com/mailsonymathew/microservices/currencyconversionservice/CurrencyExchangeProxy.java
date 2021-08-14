package com.mailsonymathew.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange", url="localhost:8000")
//@FeignClient(name="currency-exchange")

//CHANGE-KUBERNETES
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
// Note : The problem with using the K8S service host as env variable is that it depends on the Currency-Exchange service beign up. It is much better to use a customized environment variable as below which we will configure in the deployment.yaml file
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);

}
