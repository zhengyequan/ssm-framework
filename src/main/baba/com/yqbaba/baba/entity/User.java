package com.yqbaba.baba.entity;

import com.yqbaba.framework.entity.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = -4205225333816034495L;

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
