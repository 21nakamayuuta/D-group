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
    private static final String SELECT_POST_RECIPE = "SELECT pr.user_id, r.recipe_title, pr.year, pr.month, pr.day, pr.recipe_id "
            + "FROM post_recipe pr " + "JOIN recipe r ON pr.recipe_id = r.recipe_id "
            + "WHERE pr.user_id = :userId AND pr.year = :year AND pr.month = :month AND pr.day = :day";
    private static final String SELECT_ALL_POST_RECIPE = "SELECT pr.user_id, r.recipe_title, pr.year, pr.month, pr.day, pr.recipe_id "
            + "FROM post_recipe pr " + "JOIN recipe r ON pr.recipe_id = r.recipe_id " + "WHERE pr.user_id = :userId "
            + "ORDER BY pr.day, pr.month, pr.year";

    @Autowired
    private NamedParameterJdbcTemplate jT;

    @Override
    public List<PostRecipe> postRecipeList(PostRecipe postRecipe) {
        // System.out.println(postRecipe.getUserId());
        // System.out.println(postRecipe.getYear());
        // System.out.println(postRecipe.getMonth());
        // System.out.println(postRecipe.getDay());
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
}
