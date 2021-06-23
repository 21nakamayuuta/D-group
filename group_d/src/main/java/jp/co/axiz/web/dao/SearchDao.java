package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Search;

public interface SearchDao {
	public List<Search> find(String searchKeyword);
}
