package jp.co.axiz.web.dao;


import java.util.List;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
public interface UserInfoDao {

	public List<Recipe> newRecipe();
	public void insert(UserInfo user);
	public boolean isFindLoginName(String loginName);

	public UserInfo findLoginName(String loginName);

	public UserInfo findLoginNameAndPassword(String loginName, String password);
	void update_name(String name,Integer user_id);
	void update_pass(String pass,Integer user_id);


}
