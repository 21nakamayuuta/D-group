package jp.co.axiz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserInfoService;

//user_infoテーブル用サービス実装クラス
@Service
@Transactional
public class UserinfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * 認証処理
	 */
	@Override
	public UserInfo authentication(String loginName, String pass) {
		return userInfoDao.findLoginNameAndPassword(loginName, pass);
	}

	/**
	 * user_idによる検索
	 */
	@Override
	public UserInfo findLoginName(String loginName) {
		return userInfoDao.findLoginName(loginName);
	}

}
