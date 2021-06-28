package jp.co.axiz.web.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.SearchDao;
import jp.co.axiz.web.entity.Search;

@Repository
public class PgSearchDao implements SearchDao {
	private static final String SELECT = "SELECT DISTINCT r.recipe_id, r.recipe_title, r.complete_image, coalesce(g.cou, 0) good_count "
			+ "FROM recipe r ";

	private static final String JOIN_FOOD = "INNER JOIN food f ON r.recipe_id = f.recipe_id ";
	private static final String JOIN_CATEGORY = "INNER JOIN recipe_and_category rc ON r.recipe_id = rc.recipe_id ";

	private static final String OUTER_JOIN = "LEFT OUTER JOIN (SELECT recipe_id, count(recipe_id) cou FROM good_table GROUP BY recipe_id) g ON r.recipe_id = g.recipe_id ";

	private static final String WHERE = "WHERE r.recipe_title LIKE '%' || :keyword || '%' "
			+ "OR f.food_name LIKE '%' || :keyword || '%'; ";

	private static final String WHERE_CATEGORY = "WHERE category_id = :categoryNum;";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Search> find(String searchKeyword) {
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("keyword", searchKeyword);

		String sql = SELECT + JOIN_FOOD + OUTER_JOIN + WHERE;
		List<Search> searchResultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));

		return searchResultList.isEmpty() ? Collections.emptyList() : searchResultList;
	}

	@Override
	public List<Search> categoryFind(Integer categoryId) {
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("categoryNum", categoryId);

		String sql = SELECT + JOIN_CATEGORY + OUTER_JOIN + WHERE_CATEGORY;
		List<Search> searchResultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));

		return searchResultList.isEmpty() ? Collections.emptyList() : searchResultList;
	}
}