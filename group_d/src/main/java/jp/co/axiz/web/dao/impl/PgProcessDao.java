package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.ProcessDao;
import jp.co.axiz.web.entity.Process;
@Repository
public class PgProcessDao implements ProcessDao {

	private static final String REGISTER_RECIPE_AND_PROCESS = "INSERT INTO process(recipe_id, display_order_process, process_description) VALUES (:recipeId, :displayOrderProcess, :processDescription)";

	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public void registerProcess(List<Process> processList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderProcess = 1;
		String sql = REGISTER_RECIPE_AND_PROCESS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(Process p : processList) {
			param.addValue("recipeId", recipeId);
			param.addValue("displayOrderProcess", orderProcess);
			param.addValue("processDescription", p.getProcessDescription());
			orderProcess += 1;
			jT.update(sql, param);
		}
	}
}
