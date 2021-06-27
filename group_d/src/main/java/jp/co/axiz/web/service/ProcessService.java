package jp.co.axiz.web.service;

import java.util.List;
public interface ProcessService {
	public void registerProcess(List<String> processInfoList, Integer recipeId);
	public void updateProcess(List<String> processInfoList, Integer recipeId);
	public void delAndRegProcess(List<String> processInfoList, Integer recipeId);
}
