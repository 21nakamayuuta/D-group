package jp.co.axiz.web.service;

import java.util.List;

public interface FoodService {
	public void registerFood(List<String> foodNameList, List<String> amountList, Integer recipeId);
	public void updateFood(List<String> foodNameList, List<String> amountList, Integer recipeId);
}
