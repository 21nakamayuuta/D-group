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
    private static final String SELECT_MADE_RECIPE = "SELECT mr.user_id, r.recipe_title, mr.year, mr.month, mr.day, mr.recipe_id "
            + "FROM made_recipe mr " + "JOIN recipe r ON mr.recipe_id = r.recipe_id "
            + "WHERE mr.user_id = :userId AND mr.year = :year AND mr.month = :month AND mr.day = :day";
    private static final String SELECT_ALL_MADE_RECIPE = "SELECT mr.user_id, r.recipe_title, mr.year, mr.month, mr.day, mr.recipe_id "
            + "FROM made_recipe mr " + "JOIN recipe r ON mr.recipe_id = r.recipe_id " + "WHERE mr.user_id = :userId "
            + "ORDER BY mr.day, mr.month, mr.year";

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
    public List<MadeRecipe> allMadeRecipeList(Integer userId) {
        String sql = SELECT_ALL_MADE_RECIPE;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);

        List<MadeRecipe> list = jT.query(sql, param, new BeanPropertyRowMapper<MadeRecipe>(MadeRecipe.class));
        return list.isEmpty() ? Collections.emptyList() : list;
    }
}
