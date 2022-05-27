package com.nationalbank.IntegerApp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerData {
	
	public static List<Integer> RAW_DATA = new ArrayList<>();
	public static Map<Integer, Integer> INTEGER_FREQUENCY = new HashMap<>();
	public static int ZERO_COUNT;
	public static int POSITIVE_COUNT;
	public static int NEGATIVE_COUNT;
	public static List<Integer> DISTINCT_INTEGERS = new ArrayList<>();
	public static boolean IS_DATA_LOADED;	

	public static void clearData() {
		ZERO_COUNT = 0;
		POSITIVE_COUNT = 0;
		NEGATIVE_COUNT = 0;
		INTEGER_FREQUENCY = new HashMap<>();
		DISTINCT_INTEGERS = new ArrayList<>();
		IS_DATA_LOADED = false;
	}
}
