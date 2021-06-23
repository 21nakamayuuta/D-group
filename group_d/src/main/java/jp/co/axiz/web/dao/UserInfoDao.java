package jp.co.axiz.web.dao;

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
}
