package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Category;

public interface CategoryDao {
	public List<Category> searchCategory();
	public void registerRecipeAndCategory(Integer recipeId, Integer[] categoryId);
	public void deleteCategory(Integer recipeId);
}
