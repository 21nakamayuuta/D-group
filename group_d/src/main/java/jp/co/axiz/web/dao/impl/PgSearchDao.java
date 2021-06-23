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
	private static final String SELECT = "SELECT r.recipe_id, recipe_title, complete_image, count(g.recipe_id) good_count "
											+ "FROM recipe r "
											+ "INNER JOIN good_table g "
											+ "ON r.recipe_id = g.recipe_id ";

	private static final String WHERE = "WHERE g.recipe_id IN "
											+ "(SELECT r.recipe_id FROM recipe r "
											+ "INNER JOIN food f "
											+ "ON r.recipe_id = f.recipe_id "
											+ "WHERE recipe_title LIKE :keyword "
											+ "OR food_name LIKE :keyword ) ";

	private static final String WHERE_CATEGORY = "WHERE g.recipe_id IN "
											+ "(SELECT r.recipe_id FROM recipe r"
											+ "INNER JOIN recipe_and_category rc "
											+ "ON rc.recipe_id = r.recipe_id "
											+ "WHERE category_id = :categoryNum) ";

	private static final String GROUPBY = "GROUP BY recipe_title, r.recipe_id, complete_image;";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Search> find(String searchKeyword){
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("keyword", "%"+searchKeyword+"%");

		String sql = SELECT + WHERE + GROUPBY;
		List<Search> searchResultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));

		return searchResultList.isEmpty() ? null : searchResultList;
	}


	@Override
	public List<Search> categoryFind(Integer categoryId){
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("categoryNum", categoryId);

		String sql = SELECT + WHERE_CATEGORY + GROUPBY;
		List<Search> searchResultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Search>(Search.class));

		return searchResultList.isEmpty() ? null : searchResultList;
	}
}