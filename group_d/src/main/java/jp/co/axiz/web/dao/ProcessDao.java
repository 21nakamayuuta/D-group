package jp.co.axiz.web.dao;

import java.util.List;

public interface ProcessDao {
	public void registerProcess(List<String> processInfoList, Integer recipeId);
	public void updateProcess(List<String> processInfoList, Integer recipeId);
	public void deleteProcess(Integer recipeId);
}
