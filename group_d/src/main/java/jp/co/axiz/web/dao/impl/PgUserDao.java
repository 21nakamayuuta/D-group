package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.Search;

@Repository
public class PgUserDao implements UserDao {

	private static final String USER_RECIPE = "select u.user_name, r.recipe_id, r.recipe_title, complete_image,coalesce(g.cnt,0) goodCount"
			+ " from user_info u "
			+ " inner join recipe r "
			+ "  on u.user_id = r.user_id "
			+ " left join ( "
			+ "    select g.recipe_id ,count(g.good_id) cnt "
			+ "    from good_table g "
			+ "    inner join recipe r "
			+ "    on g.recipe_id = r.recipe_id "
			+ "    group by g.recipe_id) g "
			+ " on r.recipe_id = g.recipe_id "
			+ " where u.user_id =:userId ";

	//	private static final String TOTAL_RECIPE = "select u.user_id ,count(r.recipe_id) from user_info u inner join recipe r on u.user_id = r.user_id where u.user_id = :userId group by u.user_id order by u.user_id";
	//	private static final String TOTAL_GOOD = "select g.recipe_id ,count(g.good_id) from good_table g inner join recipe r on g.recipe_id = r.recipe_id where g.recipe_id = :recipeId group by g.recipe_id order by g.recipe_id";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	//	レシピ情報取得
	@Override
	public List<Search> find(Integer userId) {
		String sql = USER_RECIPE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<Search> recipeResult = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));
		return recipeResult.isEmpty() ? null : recipeResult;
	}

	//	public Integer	 totalGood(String recipeId) {
	//		String goodsql = TOTAL_RECIPE;
	//		MapSqlParameterSource param = new MapSqlParameterSource();
	//		param.addValue("recipeId".recipeId);
	//		List<User> countRecipeResult = jdbcTemplate.query(goodsql, param, new BeanPropertyRowMapper<User>(User.class));
	//		return countRecipeResult.isEmpty() ? null : countRecipeResult.get(0).getGoodCount();
	//
	//	}

}
