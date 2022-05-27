package com.nationalbank.IntegerApp.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nationalbank.IntegerApp.data.IntegerData;
import com.nationalbank.IntegerApp.data.Result;
import com.nationalbank.IntegerApp.data.StringResult;

@Service
public class IntegerServiceImpl implements IntegerService{
	
	Logger logger = LoggerFactory.getLogger(IntegerService.class); 

	@Override
	public Result processData(String data) {
		StringResult result = new StringResult();
		try {
			IntegerData.clearData();
			IntegerData.RAW_DATA = Stream.of(data.split(","))
					.map(String::trim)
					.map(Integer::parseInt)
					.collect(Collectors.toList());
			logger.info("Raw Data loaded : {}", Arrays.toString(IntegerData.RAW_DATA.toArray()));
			IntegerData.RAW_DATA.forEach(integer -> {
				if(IntegerData.INTEGER_FREQUENCY.containsKey(integer)) {
					IntegerData.INTEGER_FREQUENCY.put(integer, IntegerData.INTEGER_FREQUENCY.get(integer) + 1);
				} else {
					IntegerData.INTEGER_FREQUENCY.put(integer, 1);
					IntegerData.DISTINCT_INTEGERS.add(integer);
				}
				
				if(integer == 0) {
					IntegerData.ZERO_COUNT++;
					
				} else if (integer > 0) {
					IntegerData.POSITIVE_COUNT++;
				} else {
					IntegerData.NEGATIVE_COUNT++;
				}
			});
			logger.info("Raw Data processed : zero count = {}, positive count = {}, negative count = {}, distinct numbers = {}", 
					IntegerData.ZERO_COUNT, IntegerData.POSITIVE_COUNT, IntegerData.NEGATIVE_COUNT, 
					Arrays.toString(IntegerData.DISTINCT_INTEGERS.toArray()));
			IntegerData.IS_DATA_LOADED = true;
			result.setResult("Data Loaded Successfully");
			return result;
		} catch (Exception ex) {
			logger.error("Exception while processing raw data {}", ex);
			result.setError("Error occured while processing data. Please check the data format.");
			return result;
		}
	}

}
