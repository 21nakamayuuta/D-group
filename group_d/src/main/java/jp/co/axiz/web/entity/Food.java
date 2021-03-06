package jp.co.axiz.web.entity;

public class Food {
	private Integer displayOrderFood;
	private String foodName;
	private String amount;

	public Food() {
	}
	public Food(String foodName, String amount) {
		this.foodName = foodName;
		this.amount = amount;
	}

	public Food(Integer displayOrderFood, String foodName, String amount) {
		this.displayOrderFood = displayOrderFood;
		this.foodName = foodName;
		this.amount = amount;
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


}
