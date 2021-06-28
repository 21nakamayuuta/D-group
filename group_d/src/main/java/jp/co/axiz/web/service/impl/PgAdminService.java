package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.AdminDao;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.AdminService;

@Service
public class PgAdminService implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public List<UserInfo> userAll() {
		// TODO 自動生成されたメソッド・スタブ
		return adminDao.userAll();
	}

	@Override
	public void userDelete(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		adminDao.userDelete(userId);
	}

	@Override
	public List<Search> recipeAll() {
		// TODO 自動生成されたメソッド・スタブ
		return adminDao.recipeAll();
	}

}
