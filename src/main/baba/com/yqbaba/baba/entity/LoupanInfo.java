package com.yqbaba.baba.entity;

import java.util.Date;

import com.yqbaba.framework.entity.BaseEntity;

public class LoupanInfo extends BaseEntity {

	private static final long serialVersionUID = -5586932718208282897L;

	private String name;
	private String subName;
	private String detailDesc;
	private String developer;
	private String areaCode;
	private String indexImgs;
	private String planImgs;
	private String supportImgs;
	private String effectImgs;
	private String modelImgs;
	private String layoutImgs;
	private String link;
	private double lng;
	private double lat;
	private String price;
	private String contactName;
	private String contactPhone;
	private String contactLandLine;
	private String contactWX;
	private String contactQQ;
	private int status;
	private Date gmtOpen;
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

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getIndexImgs() {
		return indexImgs;
	}

	public void setIndexImgs(String indexImgs) {
		this.indexImgs = indexImgs;
	}

	public String getPlanImgs() {
		return planImgs;
	}

	public void setPlanImgs(String planImgs) {
		this.planImgs = planImgs;
	}

	public String getSupportImgs() {
		return supportImgs;
	}

	public void setSupportImgs(String supportImgs) {
		this.supportImgs = supportImgs;
	}

	public String getEffectImgs() {
		return effectImgs;
	}

	public void setEffectImgs(String effectImgs) {
		this.effectImgs = effectImgs;
	}

	public String getModelImgs() {
		return modelImgs;
	}

	public void setModelImgs(String modelImgs) {
		this.modelImgs = modelImgs;
	}

	public String getLayoutImgs() {
		return layoutImgs;
	}

	public void setLayoutImgs(String layoutImgs) {
		this.layoutImgs = layoutImgs;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public Date getGmtOpen() {
		return gmtOpen;
	}

	public void setGmtOpen(Date gmtOpen) {
		this.gmtOpen = gmtOpen;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

}
