package jp.co.axiz.web.dao.impl;

import java.util.List;
import java.util.Collections;
import jp.co.axiz.web.dao.MadeRecipeDao;
import jp.co.axiz.web.entity.MadeRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PgMadeRecipeDao implements MadeRecipeDao {
    private static final String CHECK_MADE_RECIPE = "SELECT user_id, date_part('year', dt) AS year,date_part('month', dt) AS month,date_part('day', dt) AS day, recipe_id FROM made_recipe "
            + "WHERE " + "recipe_id = :recipeId AND user_id = :userId AND "
            + "date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";
    private static final String SELECT_MADE_RECIPE = "SELECT mr.user_id, r.recipe_title, date_part('year', dt) as year, date_part('month', dt) as month, date_part('day', dt) as day, mr.recipe_id "
            + "FROM made_recipe mr JOIN recipe r ON mr.recipe_id = r.recipe_id "
            + "WHERE mr.user_id = :userId AND date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";
    private static final String SELECT_ALL_MADE_RECIPE = "SELECT mr.user_id, r.recipe_title, date_part('year', dt) as year, date_part('month', dt) as month, date_part('day', dt) as day, mr.recipe_id, r.user_id "
            + "FROM made_recipe mr " + "JOIN recipe r ON mr.recipe_id = r.recipe_id " + "WHERE mr.user_id = :userId "
            + "ORDER BY dt";

    private static final String INSERT_MADE_RECIPE = "INSERT INTO made_recipe VALUES (:userId, :recipeId, now())";

    private static final String DELETE_MADE_RECIPE = "DELETE FROM made_recipe WHERE user_id = :userId AND recipe_id = :recipeId AND date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";

    private static final String DELETE_MADE_BY_RECIPEID = "DELETE FROM made_recipe WHERE recipe_id = :recipeId";
    private static final String DELETE_MADE_BY_USERID = "DELETE FROM made_recipe WHERE user_id = :userId";

    @Autowired
    private NamedParameterJdbcTemplate jT;

    @Override
    public List<MadeRecipe> madeRecipeList(MadeRecipe MadeRecipe) {
        String sql = SELECT_MADE_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", MadeRecipe.getUserId());
        param.addValue("year", MadeRecipe.getYear());
        param.addValue("month", MadeRecipe.getMonth());
        param.addValue("day", MadeRecipe.getDay());

        List<MadeRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<MadeRecipe>(MadeRecipe.class));
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    @Override
    public MadeRecipe checkMadeRecipe(MadeRecipe MadeRecipe) {
        String sql = CHECK_MADE_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", MadeRecipe.getUserId());
        param.addValue("year", MadeRecipe.getYear());
        param.addValue("month", MadeRecipe.getMonth());
        param.addValue("day", MadeRecipe.getDay());
        param.addValue("recipeId", MadeRecipe.getRecipeId());

        List<MadeRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<MadeRecipe>(MadeRecipe.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<MadeRecipe> allMadeRecipeList(Integer userId) {
        String sql = SELECT_ALL_MADE_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);

        List<MadeRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<MadeRecipe>(MadeRecipe.class));
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    @Override
    public void insertMadeRecipe(Integer userId, Integer recipeId) {
        String sql = INSERT_MADE_RECIPE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("recipeId", recipeId);
        jT.update(sql, param);
    }

    @Override
    public void deleteMadeRecipe(Integer userId, Integer recipeId, Integer year, Integer month, Integer day) {
        String sql = DELETE_MADE_RECIPE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("recipeId", recipeId);
        param.addValue("year", year);
        param.addValue("month", month);
        param.addValue("day", day);
        jT.update(sql, param);
    }

    @Override
    public void deleteMadeByRecipeId(Integer recipeId) {
        String sql = DELETE_MADE_BY_RECIPEID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", recipeId);
        jT.update(sql, param);
    }

    @Override
    public void deleteMadeByUserId(Integer userId) {
        String sql = DELETE_MADE_BY_USERID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        jT.update(sql, param);
    }
}
