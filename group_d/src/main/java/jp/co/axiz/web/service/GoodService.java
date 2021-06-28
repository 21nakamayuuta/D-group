package jp.co.axiz.web.service;

public interface GoodService {
    public void insertGood(Integer recipeId, Integer userId);

    public void deleteGood(Integer goodId);
}
