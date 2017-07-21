package com.yqbaba.test.entity;

import com.yqbaba.framework.entity.BaseEntity;

public class Test extends BaseEntity {

	private static final long serialVersionUID = 8068273181470207664L;

	private String idCard;

	private String cardNo;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
