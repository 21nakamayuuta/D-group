package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.FoodDao;

@Repository
public class PgFoodDao implements FoodDao {
	private static final String REGISTER_RECIPE_AND_FOOD = "INSERT INTO food(recipe_id, display_order_food, food_name, amount) VALUES (:recipeId, :displayOrderFood, :foodName, :amount)";
	private static final String UPDATE_FOOD = "UPDATE food SET display_order_food = :displayOrderFood,food_name = :foodName, amount = :amount WHERE recipe_id = :recipeId AND display_order_food = :whereDisplayOrderFood";
	private static final String DELETE_FOOD = "DELETE FROM food WHERE recipe_id = :recipeId";

	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public void registerFood(List<String> foodNameList, List<String> amountList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderFood = 1;
		Integer count = 0;
		String sql = REGISTER_RECIPE_AND_FOOD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(String f : foodNameList) {
			param.addValue("recipeId", recipeId);
			param.addValue("displayOrderFood", orderFood);
			param.addValue("foodName", f);
			param.addValue("amount", amountList.get(count));
			orderFood += 1;
			count += 1;
			jT.update(sql, param);
		}
	}

	@Override
	public void updateFood(List<String> foodNameList, List<String> amountList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderFood = 1;
		Integer count = 0;
		String sql = UPDATE_FOOD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(String f : foodNameList) {
			param.addValue("recipeId", recipeId);
			param.addValue("whereDisplayOrderFood", orderFood);
			param.addValue("displayOrderFood", orderFood);
			param.addValue("foodName", f);
			param.addValue("amount", amountList.get(count));
			orderFood += 1;
			count += 1;
			jT.update(sql, param);
		}

	}

	@Override
	public void deleteFood(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = DELETE_FOOD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		jT.update(sql,param);
	}




}
