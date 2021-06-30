package jp.co.axiz.web.dao.impl;

import java.util.List;
import java.util.Collections;
import jp.co.axiz.web.dao.PostRecipeDao;
import jp.co.axiz.web.entity.PostRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PgPostRecipeDao implements PostRecipeDao {
    private static final String SELECT_POST_RECIPE = "SELECT pr.user_id, r.recipe_title, date_part('year', dt) as year, date_part('month', dt) as month, date_part('day', dt) as day, pr.recipe_id "
            + "FROM post_recipe pr JOIN recipe r ON pr.recipe_id = r.recipe_id "
            + "WHERE pr.user_id = :userId AND date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";
    private static final String SELECT_ALL_POST_RECIPE = "SELECT pr.user_id, r.recipe_title, date_part('year', dt) as year, date_part('month', dt) as month, date_part('day', dt) as day, pr.recipe_id "
            + "FROM post_recipe pr " + "JOIN recipe r ON pr.recipe_id = r.recipe_id " + "WHERE pr.user_id = :userId "
            + "ORDER BY dt";

    private static final String INSERT_POST_RECIPE = "INSERT INTO post_recipe VALUES (:userId, :recipeId, now())";

    private static final String DELETE_POST_RECIPE = "DELETE FROM post_recipe WHERE recipe_id = :recipeId";
    private static final String DELETE_POST_BY_USERID = "DELETE FROM post_recipe WHERE user_id = :userId";

    @Autowired
    private NamedParameterJdbcTemplate jT;

    @Override
    public List<PostRecipe> postRecipeList(PostRecipe postRecipe) {
        // postRecipe.getAllData();
        String sql = SELECT_POST_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", postRecipe.getUserId());
        param.addValue("year", postRecipe.getYear());
        param.addValue("month", postRecipe.getMonth());
        param.addValue("day", postRecipe.getDay());

        List<PostRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<PostRecipe>(PostRecipe.class));
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    @Override
    public List<PostRecipe> allPostRecipeList(Integer userId) {
        String sql = SELECT_ALL_POST_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);

        List<PostRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<PostRecipe>(PostRecipe.class));
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    @Override
    public void insertPostRecipe(Integer userId, Integer recipeId) {
        String sql = INSERT_POST_RECIPE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("recipeId", recipeId);
        jT.update(sql, param);
    }

    @Override
    public void deletePostRecipe(Integer recipeId) {
        String sql = DELETE_POST_RECIPE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", recipeId);
        jT.update(sql, param);
    }

    @Override
    public void deletePostByUserId(Integer userId) {
        String sql = DELETE_POST_BY_USERID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        jT.update(sql, param);
    }
}
