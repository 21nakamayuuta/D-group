package jp.co.axiz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.SignUpService;

@Service
public class PgSignUpService implements SignUpService {
	@Autowired
	private UserInfoDao userDao;

	@Override
	public boolean INSERT_AND_CHECK(UserInfo user) {
	//同じIDのユーザーがいるかチェック
	if(userDao.findLoginName(user.getLoginName())) {
		return false;
	}

	//いない場合インサート
	userDao.insert(user);

	return true;
}


}
