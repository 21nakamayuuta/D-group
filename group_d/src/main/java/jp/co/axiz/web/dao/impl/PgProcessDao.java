package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.ProcessDao;
@Repository
public class PgProcessDao implements ProcessDao {

	private static final String REGISTER_RECIPE_AND_PROCESS = "INSERT INTO process(recipe_id, display_order_process, process_description) VALUES (:recipeId, :displayOrderProcess, :processDescription)";
	private static final String UPDATE_PROCESS = "UPDATE process SET display_order_process = :displayOrderProcess, process_description = :processDescription WHERE recipe_id = :recipeId AND display_order_process = :whereDisplayOrderFood";
	private static final String DELETE_PROCESS = "DELETE FROM process WHERE recipe_id = :recipeId";
	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public void registerProcess(List<String> processInfoList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderProcess = 1;
		String sql = REGISTER_RECIPE_AND_PROCESS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(String p : processInfoList) {
			param.addValue("recipeId", recipeId);
			param.addValue("displayOrderProcess", orderProcess);
			param.addValue("processDescription", p);
			orderProcess += 1;
			jT.update(sql, param);
		}
	}

	@Override
	public void updateProcess(List<String> processInfoList, Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		Integer orderProcess = 1;
		Integer count = 0;
		String sql = UPDATE_PROCESS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(String p: processInfoList) {
			param.addValue("recipeId", recipeId);
			param.addValue("whereDisplayOrderFood", orderProcess);
			param.addValue("displayOrderProcess", orderProcess);
			param.addValue("processDescription", p);
			orderProcess += 1;
			jT.update(sql, param);
		}
	}

	@Override
	public void deleteProcess(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = DELETE_PROCESS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		jT.update(sql,param);
	}




}
