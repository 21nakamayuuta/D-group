package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;

public interface RecipeService {
	public List<Recipe> newRecipe();

	public List<Recipe> ranking();

	public List<Recipe> searchRecipeInfo(Integer recipeId);

	public List<Food> searchFoodInfo(Integer recipeId);

	public List<Process> searchProcessInfo(Integer recipeId);

	public Integer totalGood(Integer recipeId);

	public Recipe totalRecipe(Integer integer);

	public void registerRecipe(Recipe recipe);

	public Integer searchNewRecipe();

	public void editRecipe(Recipe recipe, Integer recipeId);

	public List<Recipe> userRecipe(Integer user_id);

	public void deleteRecipe(Integer recipe_id);

	public void editNoImageRecipe(Recipe recipe, Integer recipeId);

	public void deleteRecipeByUserId(Integer user_Id);
}
