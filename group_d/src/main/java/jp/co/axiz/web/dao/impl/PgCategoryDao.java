package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.CategoryDao;
import jp.co.axiz.web.entity.Category;

@Repository
public class PgCategoryDao implements CategoryDao{
	private static final String SEARCH_CATEGORY = "SELECT category_id, category_name FROM category";

	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public List<Category> searchCategory() {
		List<Category> resultList = jT.query(SEARCH_CATEGORY,new BeanPropertyRowMapper<Category>(Category.class));
		return resultList.isEmpty() ? null : resultList;
	}

}
