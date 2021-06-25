package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Food;

public interface FoodDao {
	public void registerFood(List<Food> foodList, Integer recipeId);
}
