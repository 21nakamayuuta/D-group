package jp.co.axiz.web.dao;

public interface GoodDao {
    public void insertGood(Integer recipeId, Integer userId);

    public void deleteGood(Integer goodId);
}