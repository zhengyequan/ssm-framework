package com.yqbaba.test.test;

import com.yqbaba.framework.entity.GPS;
import com.yqbaba.framework.util.PositionUtil;

public class Test {
	public static void main(String[] args) {
		double x = 120.29940099999999;
		double y = 30.419045;
		GPS gc = PositionUtil.gps84_To_Gcj02(y, x);
		System.out.println(gc.getWgLng()+ " ; " + gc.getWgLat());
	}
}
