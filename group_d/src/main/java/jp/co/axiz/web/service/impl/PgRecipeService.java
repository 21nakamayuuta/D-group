package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.RecipeDao;
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


}