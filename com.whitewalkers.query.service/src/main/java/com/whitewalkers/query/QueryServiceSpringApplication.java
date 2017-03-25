package com.whitewalkers.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whitewalkers.common.model.QueryHolder;
import com.whitewalkers.common.model.QuerySelectBoxItem;
import com.whitewalkers.query.service.provider.QueryServiceProvider;

@SpringBootApplication
@RestController
@RequestMapping("/queryservice")
@EnableEurekaClient
@EnableCircuitBreaker
public class QueryServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryServiceSpringApplication.class, args);
	}

	@Autowired
	private QueryServiceProvider queryServiceProvider;
	
	@HystrixCommand(fallbackMethod = "saveUserQueryFallback")
	@RequestMapping(value = "/queries", method = RequestMethod.POST)
	public boolean saveUserQuery(Map<String, Object> queryParameters) {
		
		this.queryServiceProvider.saveQuery(queryParameters);
		
		return true;
	}
	
	@HystrixCommand(fallbackMethod = "getUserQueryFallback")
	@RequestMapping(value = "/queries/{queryid}", method = RequestMethod.GET)
	public QueryHolder getUserQuery(@PathVariable("queryid") String queryid) {
		
		QueryHolder queryHolder = this.queryServiceProvider.getQueryById(queryid);
		
		return queryHolder;
	}
	
	@HystrixCommand(fallbackMethod = "getQuerySelectBoxItemListFallback")
	@RequestMapping(value = "/queryselectboxitems", method = RequestMethod.GET)
	public List<QuerySelectBoxItem> getQuerySelectBoxItemList() {
		
		List<QuerySelectBoxItem> selectBoxItemList = this.queryServiceProvider.getQuerySelectboxItems();
		
		return selectBoxItemList;
	}
	
	public boolean saveUserQueryFallback() {
		return false;
	}
	
	public QueryHolder getUserQueryFallback() {
		return new QueryHolder();
	}
	
	public List<QuerySelectBoxItem> getQuerySelectBoxItemListFallback() {
		return new ArrayList<QuerySelectBoxItem>();
	}
}
