package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Search;

public interface UserDao {

	public List<Search> find(Integer userId);

}
