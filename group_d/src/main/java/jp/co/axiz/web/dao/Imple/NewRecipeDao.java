package jp.co.axiz.web.dao.Imple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.RecipeDao;
import jp.co.axiz.web.entity.Recipe;
@Repository
public class NewRecipeDao implements RecipeDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_NEW_RECIPE = "select r.recipe_id, r.recipe_title, r.complete_image, g.good_id, r.create_datetime from recipe r join good_table g on r.recipe_id = g.recipe_id order by r.create_datetime OFFSET 0 LIMIT 6 ";

	@Override
	public List<Recipe> newRecipe() {
		List<Recipe> resultList = jdbcTemplate.query(SELECT_NEW_RECIPE,new BeanPropertyRowMapper<Recipe>(Recipe.class));
		return resultList;
	}


}
