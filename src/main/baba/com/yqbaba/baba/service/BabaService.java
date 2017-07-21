package com.yqbaba.baba.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.yqbaba.baba.dao.BabaDao;
import com.yqbaba.baba.entity.User;
import com.yqbaba.framework.service.BaseService;

@Service("babaService")
public class BabaService extends BaseService {
	@Resource
	private BabaDao babaDao;

	public User getUserById(int id) {
		return (User) transactionTemplate.execute((TransactionStatus status) -> {
			User user = new User();
			user.setName("郑叶全");
			user.setAge(23);
			babaDao.createUser(user);
			return babaDao.getUserById(id);
		});
	}

}
