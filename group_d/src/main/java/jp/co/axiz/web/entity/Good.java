package jp.co.axiz.web.entity;

public class Good {
    public Integer goodId;
    public Integer recipeId;
    public Integer userId;
    private Integer year;
    private Integer month;
    private Integer day;

    public Good() {
    };

    public Good(Integer goodId, Integer recipeId, Integer userId, Integer year, Integer month, Integer day) {
        this.goodId = goodId;
        this.recipeId = recipeId;
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.day = day;
    };

    public Good(Integer recipeId, Integer userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    };

    public Good(Integer recipeId, Integer userId, Integer year, Integer month, Integer day) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.day = day;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
