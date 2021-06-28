package jp.co.axiz.web.service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;


public class NewRecipeService implements RecipeService{
	@Autowired
	private RecipeDao recipeDao;

	@Override
	public List<Recipe> newRecipe() {
		return recipeDao.newRecipe();
	}

	@Override
	public List<Recipe> searchRecipeInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Food> searchFoodInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Process> searchProcessInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Integer totalGood(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public Recipe totalRecipe(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void registerRecipe(Recipe recipe) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Integer searchNewRecipe() {
		return null;
	}
	public List<Recipe> ranking() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void editRecipe(Recipe recipe, Integer recipeId) {

	}

	public List<Recipe> userRecipe(Integer recipe_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteRecipe(Integer recipe_id) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void editNoImageRecipe(Recipe recipe, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
