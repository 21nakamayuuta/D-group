package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Recipe;

public interface RecipeService {
	public List<Recipe> searchRecipeInfo(Integer recipeId);
}
