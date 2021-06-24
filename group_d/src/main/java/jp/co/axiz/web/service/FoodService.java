package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Food;

public interface FoodService {
	public void registerFood(List<Food> foodList, Integer recipeId);
}
