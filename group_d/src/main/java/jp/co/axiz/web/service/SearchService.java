package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Search;

public interface SearchService {
	public List<Search> find(String searchKeyword);
}
