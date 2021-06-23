package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.CategoryDao;
import jp.co.axiz.web.entity.Category;

@Repository
public class PgCategoryDao implements CategoryDao{
	private static final String SEARCH_CATEGORY = "SELECT category_id, category_name FROM category";
	private static final String REGISTER_RECIPE_AND_CATEGORY = "INSERT INTO recipe_and_category(recipe_id, category_id) VALUES (:recipeId, :categoryId)";

	@Autowired
    private NamedParameterJdbcTemplate jT;

	@Override
	public List<Category> searchCategory() {
		List<Category> resultList = jT.query(SEARCH_CATEGORY,new BeanPropertyRowMapper<Category>(Category.class));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public void registerRecipeAndCategory(Integer recipeId, Integer[] categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = REGISTER_RECIPE_AND_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		for(int i : categoryId) {
			param.addValue("recipeId", recipeId);
			param.addValue("categoryId", i);
			jT.update(sql, param);
		}
	}

}
