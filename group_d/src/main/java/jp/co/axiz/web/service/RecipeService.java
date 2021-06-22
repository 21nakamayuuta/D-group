package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;

public interface RecipeService {
	List<Recipe> newRecipe();
	public List<Recipe> searchRecipeInfo(Integer recipeId);
	public List<Food> searchFoodInfo(Integer recipeId);
	public List<Process> searchProcessInfo(Integer recipeId);
	public Integer totalGood(Integer recipeId);
}
