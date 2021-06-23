package jp.co.axiz.web.dao.impl;

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
	private static final String SELECT = "select r.recipe_id, recipe_title, complete_image, count(g.recipe_id) good_count "
			+ "from recipe r "
			+ "inner join good_table g "
			+ "on r.recipe_id = g.recipe_id "
			+ "where g.recipe_id in "
			+ "(select r.recipe_id from recipe r "
			+ "inner join food f "
			+ "on r.recipe_id = f.recipe_id "
			+ "where recipe_title like :keyword "
			+ "or food_name like :keyword ) "
			+ "group by recipe_title, r.recipe_id, complete_image;";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Search> find(String searchKeyword){
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("keyword", "%"+searchKeyword+"%");

		String sql = SELECT;
		List<Search> searchResultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));

		return searchResultList.isEmpty() ? null : searchResultList;
	}
}