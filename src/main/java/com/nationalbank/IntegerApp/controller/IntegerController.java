package com.nationalbank.IntegerApp.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nationalbank.IntegerApp.data.StringResult;
import com.nationalbank.IntegerApp.service.IntegerService;
import com.nationalbank.IntegerApp.data.IntResult;
import com.nationalbank.IntegerApp.data.ListResult;
import com.nationalbank.IntegerApp.data.Result;
import com.nationalbank.IntegerApp.data.IntegerData;


/**
 * @author sreehari
 *
 */

@RestController
public class IntegerController {
	
Logger logger = LoggerFactory.getLogger(IntegerController.class); 
@Autowired
IntegerService integerService;

/**
 * API to query the number of zeros from the list
 *
 */
@GetMapping("/integer/frequency/zero")
public Result zeroCount() {
	logger.info("uri : /integer/frequency/zero : Querying number of zeroes");
	IntResult result = new IntResult();
	if(IntegerData.IS_DATA_LOADED) {
		logger.info("Number of zeroes : {}", IntegerData.ZERO_COUNT);
		result.setResult(IntegerData.ZERO_COUNT);
	} else {
		logger.info("Data not yet loaded");
		result.setError("Data not yet loaded");
	}
	return result;

}

/**
 * API to query the number of positive numbers from the list
 *
 */
@GetMapping("/integer/frequency/positive")
public Result positiveCount() {
	logger.info("uri : /integer/frequency/positive : Querying number of positive integers");
	IntResult result = new IntResult();
	if(IntegerData.IS_DATA_LOADED) {
		logger.info("Number of positive integers : {}", IntegerData.POSITIVE_COUNT);
		result.setResult(IntegerData.POSITIVE_COUNT);
	} else {
		logger.info("Data not yet loaded");
		result.setError("Data not yet loaded");
	}
	return result;

}

/**
 * API to query the number of negative numbers from the list
 *
 */
@GetMapping("/integer/frequency/negative")
public Result negativeCount() {
	logger.info("uri : /integer/frequency/negative : Querying number of negative integers");
	IntResult result = new IntResult();
	if(IntegerData.IS_DATA_LOADED) {
		logger.info("Number of negative integers : {}", IntegerData.NEGATIVE_COUNT);
		result.setResult(IntegerData.NEGATIVE_COUNT);
	} else {
		logger.info("Data not yet loaded");
		result.setError("Data not yet loaded");
	}
	return result;

}

/**
 * API to query the number of times a number appear in the list
 *
 */
@GetMapping("/integer/frequency/{integer}")
public Result getCount(@PathVariable Integer integer) {
	logger.info("uri : /integer/frequency/*integer : Querying number of occurences of {}", integer);
	IntResult result = new IntResult();
	if(IntegerData.IS_DATA_LOADED) {
		if(IntegerData.INTEGER_FREQUENCY.containsKey(integer)) {
		logger.info("Number of negative occurences of {} : {}", integer, IntegerData.INTEGER_FREQUENCY.get(integer));
		result.setResult(IntegerData.INTEGER_FREQUENCY.get(integer));
		} else {
			logger.info("Number not present in data set");
			result.setResult(0);
		}
	} else {
		logger.info("Data not yet loaded");
		result.setError("Data not yet loaded");
	}
	return result;				
}

/**
 * API to query the distinct numbers from the list
 *
 */
@GetMapping("/integer/distinct")
@ResponseBody
public Result getDistinct() {
	logger.info("uri : /integer/frequency/distinct : Querying distinct integers");
	ListResult result = new ListResult();
	if(IntegerData.IS_DATA_LOADED) {
		logger.info("Distinct Integers : {} ", Arrays.toString(IntegerData.DISTINCT_INTEGERS.toArray()));
		result.setResult(IntegerData.DISTINCT_INTEGERS);
	} else {
		logger.info("Data not yet loaded");
		result.setError("Data not yet loaded");
	}
	return result;			
}

/**
 * API to load data to the list - preferred method
 *
 */
@PostMapping("/integer/loadData")
public Result loadData(@RequestBody String data) {
	logger.info("uri : /integer/loadData (POST) : Loading new data {}", data);
	StringResult result = new StringResult();
	if(null!= data  && !data.trim().isEmpty()) {
		return integerService.processData(data);
	} else {
		logger.info("Data received is null, returning");
		result.setError("Data recieved is null or blank");

	}
	return result;
	
}

/**
 * API to load data to the list - only if the data set is small. Please use the POST API for larger data. 
 *
 */
@GetMapping("/integer/loadData/{data}")
public Result loadSmallData(@PathVariable String data) {
	logger.info("uri : /integer/loadData (GET) : Loading new data {}", data);
	StringResult result = new StringResult();
	if(null!= data  && !data.trim().isEmpty()) {
		return integerService.processData(data);
	} else {
		logger.info("Data received is null, returning");
		result.setError("Data recieved is null or blank");

	}
	return result;
	
}

/**
 * API to clear the loaded data. 
 *
 */
@GetMapping("/integer/clearData")
public Result clearData() {
	logger.info("uri : /integer/clearData : calling clear data service");
	StringResult result = new StringResult();
	IntegerData.clearData();
	result.setResult("Data Cleared Succesfully");
	return result;
	
}

}
