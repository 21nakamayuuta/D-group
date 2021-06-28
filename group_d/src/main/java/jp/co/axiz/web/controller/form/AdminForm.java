package jp.co.axiz.web.controller.form;

import java.util.List;

public class AdminForm {
	private List<String> categoryNameList;
	private List<Integer> categoryIdList;


	public List<String> getCategoryNameList() {
		return categoryNameList;
	}

	public void setCategoryNameList(List<String> categoryName) {
		this.categoryNameList = categoryName;
	}

	public List<Integer> getCategoryIdList() {
		return categoryIdList;
	}

	public void setCategoryIdList(List<Integer> categoryId) {
		this.categoryIdList = categoryId;
	}

}
