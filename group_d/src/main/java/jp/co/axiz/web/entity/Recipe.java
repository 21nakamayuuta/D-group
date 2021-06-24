package jp.co.axiz.web.entity;

import java.sql.Timestamp;

public class Recipe {
	private Integer recipeId;
	private Integer userId;
	private String recipeTitle;
	private String completeImage;
	private Integer cookingTime;
	private String overview;
	private Timestamp createDateTime;
	private Timestamp updateDateTime;

	private String userName;
	private String categoryName;
    private Integer goodCount;

    private Integer recipeCount;



	public Recipe() {
	}
	public Recipe(Integer userId, String recipeTitle, String completeImage, Integer cookingTime, String overview,
			Timestamp createDateTime) {
		this.userId = userId;
		this.recipeTitle = recipeTitle;
		this.completeImage = completeImage;
		this.cookingTime = cookingTime;
		this.overview = overview;
		this.createDateTime = createDateTime;
	}
	public Integer getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getCompleteImage() {
		return completeImage;
	}
	public void setCompleteImage(String completeImage) {
		this.completeImage = completeImage;
	}
	public Integer getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(Integer cookingTime) {
		this.cookingTime = cookingTime;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public Integer getRecipeCount() {
		return recipeCount;
	}
	public void setRecipeCount(Integer recipeCount) {
		this.recipeCount = recipeCount;
	}



}
