package jp.co.axiz.web.dao;

import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {

	public UserInfo findLoginName(String loginName);

	public UserInfo findLoginNameAndPassword(String loginName, String password);

}
