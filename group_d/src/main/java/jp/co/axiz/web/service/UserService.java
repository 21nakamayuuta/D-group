package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Search;

public interface UserService {

	public List<Search> find(Integer userId);

}
