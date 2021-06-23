package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;

public interface RecipeDao {
	public List<Recipe> newRecipe();
	public List<Recipe> searchRecipeInfo(Integer recipeId);
	public List<Food> searchFoodInfo(Integer recipeId);
	public List<Process> searchProcessInfo(Integer recipeId);
	public Integer totalGood(Integer recipeId);
	public void registerRecipe(Recipe recipe);
	public Integer searchNewRecipe();
}
