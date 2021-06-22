package jp.co.axiz.web.dao;


import java.util.List;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {
	public List<Recipe> newRecipe();
	public void insert(UserInfo user);
	public boolean findLoginName(String loginName);
}
