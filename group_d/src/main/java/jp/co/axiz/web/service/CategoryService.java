package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Category;

public interface CategoryService {
	public List<Category> searchCategory();
	public void registerRecipeAndCategory(Integer recipeId, Integer[] categoryId);
}
