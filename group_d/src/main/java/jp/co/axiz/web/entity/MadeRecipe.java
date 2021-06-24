package jp.co.axiz.web.entity;

public class MadeRecipe {
	private Integer userId;
	private String recipeTitle;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer recipeId;

	public MadeRecipe() {
	}

	public MadeRecipe(Integer userId, String recipeTitle, Integer year, Integer month, Integer day, Integer recipeId) {
		this.userId = userId;
		this.recipeTitle = recipeTitle;
		this.year = year;
		this.month = month;
		this.day = day;
		this.recipeId = recipeId;
	}

	public MadeRecipe(Integer userId, Integer year, Integer month, Integer day) {
		this.userId = userId;
		this.year = year;
		this.month = month;
		this.day = day;
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

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public void getAllData() {
		System.out.println(this.userId + ", " + this.recipeTitle + ", " + this.year + ", " + this.month + ", "
				+ this.day + ", " + this.recipeId);
	}
}
