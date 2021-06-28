package jp.co.axiz.web.dao.impl;

import java.util.ArrayList;
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
	private static final String SEARCH_CATEGORY = "SELECT category_id, category_name FROM category ORDER BY category_id";
	private static final String REGISTER_RECIPE_AND_CATEGORY = "INSERT INTO recipe_and_category(recipe_id, category_id) VALUES (:recipeId, :categoryId)";
	private static final String INSERT_CATEGORY = "INSERT INTO category(category_name) VALUES (:categoryName)";
	private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category_id = :categoryId";
	private static final String UPDATE_CATEGORY = "UPDATE category SET category_name = :categoryName WHERE category_id = :categoryId ";
	private static final String DELETE_RECIPE_AND_CATEGORY = "DELETE FROM recipe_and_category WHERE recipe_id = :recipeId";
	private static final String SELECT_CATEGORY = "SELECT category_id FROM recipe_and_category WHERE recipe_id = :recipeId";

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

	@Override
	public void insertCategory(String categoryName) {
		String sql = INSERT_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("categoryName", categoryName);
		jT.update(sql, param);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		String sql = DELETE_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("categoryId", categoryId);
		jT.update(sql, param);
	}

	@Override
	public void updateCategory(Integer categoryId, String categoryName) {
		String sql = UPDATE_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("categoryId", categoryId);
		param.addValue("categoryName", categoryName);
		jT.update(sql, param);
	}


	public void deleteRecipeAndCategory(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = DELETE_RECIPE_AND_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		jT.update(sql,param);
	}

	@Override
	public List<Integer> selectCategory(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = SELECT_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Category> result = jT.query(sql,param,new BeanPropertyRowMapper<Category>(Category.class));
		List<Integer> intResult = new ArrayList<Integer>();
		for(Category c : result) {
			intResult.add(c.getCategoryId());
		}
		return intResult.isEmpty() ? null : intResult;
	}
}
