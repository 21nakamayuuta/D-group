package jp.co.axiz.web.dao;

import java.util.List;

public interface FoodDao {
	public void registerFood(List<String> foodNameList, List<String> amountList, Integer recipeId);
	public void updateFood(List<String> foodNameList, List<String> amountList, Integer recipeId);
	public void deleteFood(Integer recipeId);

}
