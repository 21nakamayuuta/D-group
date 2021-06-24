package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Process;

public interface ProcessDao {
	public void registerProcess(List<Process> processList, Integer recipeId);
}
