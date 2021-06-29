package jp.co.axiz.web.dao;

import jp.co.axiz.web.entity.Good;
import jp.co.axiz.web.entity.Recipe;

public interface GoodDao {
    public Good todaysChecked(Good good);

    public void insertGood(Good good);

    public void deleteGood(Good good);

    public void deleteGoodByRecipeId(Integer recipeId);

    public void deleteGoodByUserId(Integer userId);
}