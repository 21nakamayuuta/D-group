package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.FoodDao;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.service.FoodService;

@Service
public class PgFoodService implements FoodService {
	@Autowired
	FoodDao foodDao;

	@Override
	public void registerFood(List<Food> foodList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		foodDao.registerFood(foodList, recipeId);
	}

	@Override
	public void updateFood(List<String> foodNameList, List<String> amountList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		foodDao.updateFood(foodNameList,amountList,recipeId);
	}

}
