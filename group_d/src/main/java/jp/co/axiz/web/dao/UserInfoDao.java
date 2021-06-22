package jp.co.axiz.web.dao;

import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {
	public void insert(UserInfo user);
	public boolean findLoginName(String loginName);
}
