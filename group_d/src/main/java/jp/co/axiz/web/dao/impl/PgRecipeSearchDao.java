package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Recipe;
@Repository
public class PgRecipeSearchDao implements RecipeDao {
	private static final String SEARCH_RECIPE_INFO = "SELECT r.recipe_title, complete_image, cooking_time, overview, category_name, r.user_id,user_name "
														 + "FROM recipe r JOIN recipe_and_category rac ON r.recipe_id = rac.recipe_id JOIN category c ON rac.category_id = c.category_id "
														 + "JOIN user_info ui ON r.user_id = ui.user_id WHERE r.recipe_id = :recipeId ";

//	private static final String SEARCH_FOOD_INFO = ""
	@Autowired
    private NamedParameterJdbcTemplate jT;


	@Override
	public List<Recipe> searchRecipeInfo(Integer recipeId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = SEARCH_RECIPE_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("recipeId", recipeId);
		List<Recipe> resultList = jT.query(sql, param, new
				BeanPropertyRowMapper<Recipe>(Recipe.class));

		return resultList.isEmpty() ? null : resultList;

	}


}
