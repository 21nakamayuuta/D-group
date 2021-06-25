package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.AdminDao;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;

@Repository
public class PgAdminDao implements AdminDao {
	private static final String SELECT_USER = "SELECT user_id, login_name, user_name, password, role_id FROM user_info";
	private static final String DELETE_USER = "DELETE FROM user_info WHERE user_id = :userId";
	private static final String RECIPE_ALL = "select distinct u.user_name, r.recipe_id, r.recipe_title, r.complete_image, coalesce(g.cou, 0) good_count "
												+"from recipe r inner join user_info u on r.user_id = u.user_id "
												+"left outer join (select recipe_id, count(recipe_id) cou from good_table group by recipe_id) g "
												+"on r.recipe_id = g.recipe_id ";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


	@Override
	public List<UserInfo> userAll(){
		String sql = SELECT_USER;
		List<UserInfo> selectUserList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return selectUserList.isEmpty() ? null : selectUserList;
	}

	@Override
	public void userDelete(Integer userId) {
		String sql = DELETE_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
		jdbcTemplate.update(sql, param);
	}


	@Override
	public List<Search> recipeAll() {
		String sql = RECIPE_ALL;
		List<Search> recipeAllList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Search>(Search.class));

		return recipeAllList.isEmpty() ? null : recipeAllList;
	}


}
