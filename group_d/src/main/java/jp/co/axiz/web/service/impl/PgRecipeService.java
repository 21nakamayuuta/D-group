package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Service
public class PgRecipeService implements RecipeService {
	@Autowired
	RecipeDao recipeDao;

//	レシピ情報取得
	@Override
	public List<Recipe> searchRecipeInfo(Integer recipeId) {
		return recipeDao.searchRecipeInfo(recipeId);
	}

	@Override
	public List<Food> searchFoodInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return recipeDao.searchFoodInfo(recipeId);
	}

	@Override
	public List<Process> searchProcessInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return recipeDao.searchProcessInfo(recipeId);
	}

	@Override
	public Integer totalGood(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		return recipeDao.totalGood(recipeId);
	}

	@Override
	public List<Recipe> newRecipe() {
		return recipeDao.newRecipe();
	}

	@Override
	public Recipe totalRecipe(Integer userId) {
		Recipe recipe= null;
		List<Recipe> resultList = null;
		resultList = recipeDao.totalRecipe(userId);
		try{
			recipe = resultList.get(0);
		} catch(Exception e) {
		}
		return recipe;
	}




}
