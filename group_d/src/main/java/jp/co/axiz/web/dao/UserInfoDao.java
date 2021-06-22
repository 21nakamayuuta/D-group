package jp.co.axiz.web.dao;

<<<<<<< HEAD
import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoDao {
	public void insert(UserInfo user);
	public boolean findLoginName(String loginName);
=======
import java.util.List;

import jp.co.axiz.web.entity.Recipe;

public interface UserInfoDao {
	public List<Recipe> newRecipe();
>>>>>>> 677b7b9dd6e07af69e17099d32f09650258d5043
}
