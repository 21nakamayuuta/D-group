package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.SearchDao;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.service.SearchService;

@Service
public class PgSearchService implements SearchService {
	@Autowired
	SearchDao searchDao;

	//検索キーワードを取得
	@Override
	public List<Search> find(String searchKeyword) {
		// TODO 自動生成されたメソッド・スタブ
		return searchDao.find(searchKeyword);
	}

	//カテゴリーIDで検索
	@Override
	public List<Search> categoryFind(Integer categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		return searchDao.categoryFind(categoryId);
	}
}
