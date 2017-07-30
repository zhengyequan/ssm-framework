package com.yqbaba.baba.entity;

import java.util.Date;

import com.yqbaba.framework.entity.BaseEntity;

public class LoupanInfo extends BaseEntity {

	private static final long serialVersionUID = -5586932718208282897L;

	private String name;
	private String subName;
	private String detailDesc;
	private String imgPath;
	private double lng;
	private double lat;
	private String price;
	private String contactName;
	private String contactPhone;
	private String contactLandLine;
	private String contactWX;
	private String contactQQ;
	private int status;
	private Date gmtCreated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactLandLine() {
		return contactLandLine;
	}

	public void setContactLandLine(String contactLandLine) {
		this.contactLandLine = contactLandLine;
	}

	public String getContactWX() {
		return contactWX;
	}

	public void setContactWX(String contactWX) {
		this.contactWX = contactWX;
	}

	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

}
