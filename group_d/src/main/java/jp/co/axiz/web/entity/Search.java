package jp.co.axiz.web.entity;

public class Search {

	private String userId;
	private String userName;
	private Integer recipeId;
	private String recipeTitle;
	private String completeImage;
	private Integer goodCount;

	public Search() {
	}

	public Search(Integer recipeId, String recipeTitle, String completeImage, Integer goodCount) {
		this.recipeId = recipeId;
		this.recipeTitle = recipeTitle;
		this.completeImage = completeImage;
		this.goodCount = goodCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

}
