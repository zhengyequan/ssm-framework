package com.yqbaba.framework.util;

import com.yqbaba.framework.entity.GPS;

public class PositionUtil {
	/**
	 * 各地图API坐标系统比较与转换;
	 * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系,
	 * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）;
	 * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。
	 * 谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系;
	 * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。 chenhua
	 */

	public static final String BAIDU_LBS_TYPE = "bd09ll";

	public static double pi = 3.1415926535897932384626;
	public static double a = 6378245.0;
	public static double ee = 0.00669342162296594323;

	/**
	 * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public static GPS gps84_To_Gcj02(double lat, double lng) {
		if (outOfChina(lat, lng)) {
			return null;
		}
		double dLat = transformLat(lng - 105.0, lat - 35.0);
		double dLng = transformLon(lng - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLng = (dLng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		double mgLat = lat + dLat;
		double mgLng = lng + dLng;
		return new GPS(mgLat, mgLng);
	}

	/**
	 * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
	 */
	public static GPS gcj_To_Gps84(double lat, double lng) {
		GPS gps = transform(lat, lng);
		double lngtitude = lng * 2 - gps.getWgLng();
		double latitude = lat * 2 - gps.getWgLat();
		return new GPS(latitude, lngtitude);
	}

	/**
	 * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
	 * 
	 * @param gg_lat
	 * @param gg_lng
	 */
	public static GPS gcj02_To_Bd09(double gg_lat, double gg_lng) {
		double x = gg_lng, y = gg_lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
		double bd_lon = z * Math.cos(theta) + 0.0065;
		double bd_lng = z * Math.sin(theta) + 0.006;
		return new GPS(bd_lng, bd_lon);
	}

	/**
	 * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 *
	 * * @param bd_lat * @param bd_lon * @return
	 */
	public static GPS bd09_To_Gcj02(double bd_lat, double bd_lng) {
		double x = bd_lng - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
		double gg_lng = z * Math.cos(theta);
		double gg_lat = z * Math.sin(theta);
		return new GPS(gg_lat, gg_lng);
	}

	/**
	 * (BD-09)-->84
	 * 
	 * @param bd_lat
	 * @param bd_lon
	 * @return
	 */
	public static GPS bd09_To_Gps84(double bd_lat, double bd_lon) {
		GPS gcj02 = bd09_To_Gcj02(bd_lat, bd_lon);
		GPS map84 = gcj_To_Gps84(gcj02.getWgLat(), gcj02.getWgLng());
		return map84;
	}

	public static boolean outOfChina(double lat, double lng) {
		if (lng < 72.004 || lng > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	public static GPS transform(double lat, double lng) {
		if (outOfChina(lat, lng)) {
			return new GPS(lat, lng);
		}
		double dLat = transformLat(lng - 105.0, lat - 35.0);
		double dLon = transformLon(lng - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		double mgLat = lat + dLat;
		double mgLng = lng + dLon;
		return new GPS(mgLat, mgLng);
	}

	public static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	public static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

}
