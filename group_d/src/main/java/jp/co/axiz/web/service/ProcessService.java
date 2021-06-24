package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Process;
public interface ProcessService {
	public void registerProcess(List<Process> processList, Integer recipeId);
}
