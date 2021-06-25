package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;


@Repository
public class PgRecipeDao implements RecipeDao{
	private static final String SEARCH_RECIPE_INFO = "SELECT r.recipe_title, complete_image, cooking_time, overview, category_name, r.user_id,user_name "
			 + "FROM recipe r JOIN recipe_and_category rac ON r.recipe_id = rac.recipe_id JOIN category c ON rac.category_id = c.category_id "
			 + "JOIN user_info ui ON r.user_id = ui.user_id WHERE r.recipe_id = :recipeId ";

	private static final String SEARCH_FOOD_INFO = "SELECT f.food_name, amount FROM recipe r JOIN food f ON r.recipe_id = f.recipe_id WHERE r.recipe_id = :recipeId ORDER BY display_order_food asc";
	private static final String SEARCH_PROCESS_INFO = "SELECT process_description FROM recipe r JOIN process p ON r.recipe_id = p.recipe_id WHERE r.recipe_id = :recipeId ORDER BY display_order_process asc";
	private static final String TOTAL_GOOD = "SELECT COUNT(g.good_id) AS goodCount FROM recipe r JOIN good_table g ON r.recipe_id = g.recipe_id WHERE r.recipe_id = :recipeId GROUP BY r.recipe_id";
	private static final String SELECT_NEW_RECIPE = "select r.recipe_id,r.recipe_title,r.complete_image,count(g.good_id) as goodCount,r.create_datetime from recipe r join good_table g on r.recipe_id = g.recipe_id GROUP BY r.recipe_id order by r.create_datetime desc OFFSET 0 LIMIT 6";
	private static final String SELECT_RANKING = "select r.recipe_id,r.recipe_title,r.complete_image,count(g.good_id) as goodCount,r.create_datetime from recipe r join good_table g on r.recipe_id = g.recipe_id GROUP BY r.recipe_id order by goodCount desc OFFSET 0 LIMIT 3";
	private static final String REGISTER_RECIPE="INSERT INTO recipe(user_id, recipe_title, complete_image, cooking_time, overview, create_datetime) VALUES (:userId, :recipeTitle, :completeImage, :cookingTime, :overview, :createDateTime)";
	private static final String SEARCH_NEW_RECIPE= "SELECT recipe_id FROM recipe ORDER BY create_datetime desc OFFSET 0 LIMIT 1";
	private static final String SELECT_RECIPE_TOTAL = "select count(recipe_id) as recipeCount from recipe where user_id=:user_id group by user_id;";
	private static final String USER_RECIPE ="select r.*, coalesce(g.cnt, 0) as goodCount from recipe r left join (select recipe_id, count(*) cnt from good_table group by recipe_id) g on r.recipe_id = g.recipe_id where r.user_id = :user_id";
	private static final String DELETE_RECIPE = "delete from recipe where recipe_id=:recipe_id";

	private static final String EDIT_RECIPE="UPDATE recipe SET recipe_title = :recipeTitle, complete_image = :completeImage, cooking_time = :cookingTime, overview = :overview, update_datetime = :updateDateTime WHERE recipe_id = :recipeID";

	@Autowired
	private NamedParameterJdbcTemplate jT;

	@Override
	public List<Recipe> newRecipe() {
		List<Recipe> resultList = jT.query(SELECT_NEW_RECIPE,new BeanPropertyRowMapper<Recipe>(Recipe.class));
		return resultList;
	}

	@Override
	public List<Recipe> ranking() {
		List<Recipe> rankingList = jT.query(SELECT_RANKING,new BeanPropertyRowMapper<Recipe>(Recipe.class));
		return rankingList;
	}


	@Override
	public List<Recipe> searchRecipeInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = SEARCH_RECIPE_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Recipe> resultList = jT.query(sql, param, new
		BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList.isEmpty() ? null : resultList;

	}


	@Override
	public List<Food> searchFoodInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = SEARCH_FOOD_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Food> resultList = jT.query(sql, param, new
		BeanPropertyRowMapper<Food>(Food.class));

		return resultList.isEmpty() ? null : resultList;
	}


	@Override
	public List<Process> searchProcessInfo(Integer recipeId) {
		String sql = SEARCH_PROCESS_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Process> resultList = jT.query(sql, param, new
		BeanPropertyRowMapper<Process>(Process.class));

		return resultList.isEmpty() ? null : resultList;
	}


	@Override
	public Integer totalGood(Integer recipeId) {
		String sql = TOTAL_GOOD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Recipe> resultList = jT.query(sql, param, new
		BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList.isEmpty() ? null : resultList.get(0).getGoodCount();
	}


	@Override
	public void registerRecipe(Recipe recipe) {
	// TODO 自動生成されたメソッド・スタブ

		String sql = REGISTER_RECIPE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", recipe.getUserId());
		param.addValue("recipeTitle", recipe.getRecipeTitle());
		param.addValue("completeImage", recipe.getCompleteImage());
		param.addValue("cookingTime", recipe.getCookingTime());
		param.addValue("overview", recipe.getOverview());
		param.addValue("createDateTime", recipe.getCreateDateTime());
		jT.update(sql, param);
	}


	@Override
	public Integer searchNewRecipe() {
		String sql = SEARCH_NEW_RECIPE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		List<Recipe> resultList = jT.query(sql, param, new
		BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList.isEmpty() ? null : resultList.get(0).getRecipeId();
	}

	@Override
	public void editRecipe(Recipe recipe, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = EDIT_RECIPE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeTitle", recipe.getRecipeTitle());
		param.addValue("completeImage", recipe.getCompleteImage());
		param.addValue("cookingTime", recipe.getCookingTime());
		param.addValue("overview", recipe.getOverview());
		param.addValue("updateDateTime", recipe.getUpdateDateTime());
		param.addValue("recipeId", recipeId);
		jT.update(sql, param);
	}



	@Override
	public List<Recipe> totalRecipe(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);

		List<Recipe> resultList = jT.query(SELECT_RECIPE_TOTAL,param,new BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList;
	}

	@Override
	public List<Recipe> userRecipe(Integer user_id){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);

		List<Recipe> resultList = jT.query(USER_RECIPE,param,new BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList;
	}

	@Override
	public void deleteRecipe(Integer recipe_id) {
	// TODO 自動生成されたメソッド・スタブ

		String sql = DELETE_RECIPE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipe_id", recipe_id);
		jT.update(sql, param);
	}



}
