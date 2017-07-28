package com.yqbaba.framework.entity;

public class GPS {
	private double wgLat;
	private double wgLng;

	public GPS(double wgLat, double wgLng) {
		setWgLat(wgLat);
		setWgLng(wgLng);
	}

	public double getWgLat() {
		return wgLat;
	}

	public void setWgLat(double wgLat) {
		this.wgLat = wgLat;
	}

	public double getWgLng() {
		return wgLng;
	}

	public void setWgLng(double wgLng) {
		this.wgLng = wgLng;
	}

}
