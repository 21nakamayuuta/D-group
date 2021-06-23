package jp.co.axiz.web.service;

import jp.co.axiz.web.entity.UserInfo;

//user_infoテーブル用サービスインターフェース

public interface UserInfoService {

	//	ログイン機能
	public UserInfo authentication(String loginName, String pass);

	public UserInfo findLoginName(String loginName);

}
