package jp.co.axiz.web.service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Service
public class NewRecipeService implements RecipeService{
	@Autowired
	private RecipeDao recipeDao;

	@Override
	public List<Recipe> newRecipe() {
		return recipeDao.newRecipe();
	}

}
