package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.CategoryDao;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.service.CategoryService;

@Service
public class PgCategoryService implements CategoryService{
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> searchCategory() {
		// TODO 自動生成されたメソッド・スタブ
		return categoryDao.searchCategory();
	}


}
