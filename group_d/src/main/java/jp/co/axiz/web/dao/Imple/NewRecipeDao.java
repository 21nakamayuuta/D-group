package jp.co.axiz.web.dao.Imple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;

public class NewRecipeDao implements RecipeDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_NEW_RECIPE = "select r.recipe_id, r.recipe_title, r.complete_image, g.good_id, r.create_datetime from recipe r join good_table g on r.recipe_id = g.recipe_id order by r.create_datetime OFFSET 0 LIMIT 6 ";

	@Override
	public List<Recipe> newRecipe() {
		List<Recipe> resultList = jdbcTemplate.query(SELECT_NEW_RECIPE,new BeanPropertyRowMapper<Recipe>(Recipe.class));
		return resultList;
	}

	@Override
	public List<Recipe> ranking() {
		List<Recipe> rankingList = jdbcTemplate.query(SELECT_NEW_RECIPE,new BeanPropertyRowMapper<Recipe>(Recipe.class));
		return rankingList;
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
	public List<Recipe> totalRecipe(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void registerRecipe(Recipe recipe) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Integer searchNewRecipe() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void editRecipe(Recipe recipe, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public List<Recipe> userRecipe(Integer recipeId) {
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
