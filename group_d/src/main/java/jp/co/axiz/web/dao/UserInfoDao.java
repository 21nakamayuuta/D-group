package jp.co.axiz.web.dao;

<<<<<<< HEAD

import java.util.List;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {
	public List<Recipe> newRecipe();
	public void insert(UserInfo user);
	public boolean findLoginName(String loginName);
=======
//<<<<<<< HEAD
import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {

	public UserInfo findLoginName(String loginName);

	public UserInfo findLoginNameAndPassword(String loginName, String password);

	//=======
	//import java.util.List;
	//
	//import jp.co.axiz.web.entity.Recipe;
	//
	//public interface UserInfoDao {
	//	public List<Recipe> newRecipe();
	//>>>>>>> b30b2e0d75a6f8f92537301522f4a00410c57492
>>>>>>> 7f407d88f7c0a1dd9413d2edbfec8df0bc7a736c
}
