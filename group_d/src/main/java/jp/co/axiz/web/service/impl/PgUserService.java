package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.service.UserService;

@Service
public class PgUserService implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public List<Search> find(Integer userId) {
		return userDao.find(userId);
	}

}
