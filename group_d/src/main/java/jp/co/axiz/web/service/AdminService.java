package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;

public interface AdminService {
	public List<UserInfo> userAll();
	public void userDelete(Integer userId);
	public List<Search> recipeAll();
}
