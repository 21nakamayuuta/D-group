package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.ProcessDao;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.service.ProcessService;

@Service
public class PgProcessService implements ProcessService {

	@Autowired
	ProcessDao processDao;
	@Override
	public void registerProcess(List<Process> processList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		processDao.registerProcess(processList, recipeId);
	}

}
