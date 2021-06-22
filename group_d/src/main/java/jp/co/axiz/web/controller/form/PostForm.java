package jp.co.axiz.web.controller.form;

import org.springframework.web.multipart.MultipartFile;

public class PostForm {
	private String recipeTitle;
	private MultipartFile completeImage;
	private Integer displayOrderFood;
	private String foodName;
	private String amount;
	private Integer cookingTime;
	private Integer displayOrderProcess;
	private String processDescription;
	private String overview;
	private Integer categoryId;
	private String categoryName;





	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public MultipartFile getCompleteImage() {
		return completeImage;
	}
	public void setCompleteImage(MultipartFile completeImage) {
		this.completeImage = completeImage;
	}


	public Integer getDisplayOrderFood() {
		return displayOrderFood;
	}
	public void setDisplayOrderFood(Integer displayOrderFood) {
		this.displayOrderFood = displayOrderFood;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(Integer cookingTime) {
		this.cookingTime = cookingTime;
	}
	public Integer getDisplayOrderProcess() {
		return displayOrderProcess;
	}
	public void setDisplayOrderProcess(Integer displayOrderProcess) {
		this.displayOrderProcess = displayOrderProcess;
	}
	public String getProcessDescription() {
		return processDescription;
	}
	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




}
