package jp.co.axiz.web.entity;

public class Good {
    public Integer goodId;
    public Integer recipeId;
    public Integer userId;

    public Good() {
    };

    public Good(Integer goodId, Integer recipeId, Integer userId) {
        this.goodId = goodId;
        this.recipeId = recipeId;
        this.userId = userId;
    };

    public Good(Integer recipeId, Integer userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    };

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
