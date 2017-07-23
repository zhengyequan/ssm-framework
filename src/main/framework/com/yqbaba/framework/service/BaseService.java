package com.yqbaba.framework.service;

import javax.annotation.Resource;

import org.springframework.transaction.support.TransactionTemplate;

public class BaseService {

	@Resource
	protected TransactionTemplate transactionTemplate;

}
