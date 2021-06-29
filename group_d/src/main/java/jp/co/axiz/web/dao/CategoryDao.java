package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Category;

public interface CategoryDao {
	public List<Category> searchCategory();
	public void registerRecipeAndCategory(Integer recipeId, Integer[] categoryId);
	public void insertCategory(String categoryName);
	public void deleteCategory(Integer categoryId);
	public void updateCategory(Integer categoryId, String categoryName);
	public void deleteRecipeAndCategory(Integer recipeId);
	public List<Integer> selectCategory(Integer recipeId);
	public List<Category> searchCategoryName(Integer recipeId);
}
