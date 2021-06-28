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

	@Override
	public void registerRecipeAndCategory(Integer recipeId, Integer[] categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		categoryDao.registerRecipeAndCategory(recipeId, categoryId);

	}

	@Override
	public void insertCategory(String categoryName) {
		// TODO 自動生成されたメソッド・スタブ
		categoryDao.insertCategory(categoryName);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		categoryDao.deleteCategory(categoryId);
	}

	@Override
	public void updateCategory(Integer categoryId, String categoryName) {
		// TODO 自動生成されたメソッド・スタブ
		categoryDao.updateCategory(categoryId, categoryName);
	}

	@Override
	public void deleteRecipeAndCategory(Integer recipeId, Integer[] categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		categoryDao.deleteRecipeAndCategory(recipeId);
		categoryDao.registerRecipeAndCategory(recipeId, categoryId);
	}

	@Override
	public List<Integer> selectCategory(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return categoryDao.selectCategory(recipeId);
	}


}
