package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.FoodDao;
import jp.co.axiz.web.entity.Food;

@Repository
public class PgFoodDao implements FoodDao {
	private static final String REGISTER_RECIPE_AND_FOOD = "INSERT INTO food(recipe_id, display_order_food, food_name, amount) VALUES (:recipeId, :displayOrderFood, :foodName, :amount)";
	private static final String UPDATE_FOOD = "UPDATE food SET display_order_food = :displayOrderFood,food_name = :foodName, amount = :amount WHERE recipe_id = :recipeId AND display_order_food = :whereDisplayOrderFood";

	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public void registerFood(List<Food> foodList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderFood = 1;
		String sql = REGISTER_RECIPE_AND_FOOD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(Food f : foodList) {
			param.addValue("recipeId", recipeId);
			param.addValue("displayOrderFood", orderFood);
			param.addValue("foodName", f.getFoodName());
			param.addValue("amount", f.getAmount());
			orderFood += 1;
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


}
