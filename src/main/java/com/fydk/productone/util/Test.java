package com.fydk.productone.util;

import java.util.HashMap;
import java.util.Map;

import com.fydk.productone.bean.FydkProduct;

public class Test {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put("1", new FydkProduct());
		map.put("2",new FydkProduct());
		System.out.println(map);
	}
}
