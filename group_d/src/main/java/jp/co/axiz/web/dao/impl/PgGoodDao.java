package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.GoodDao;
import jp.co.axiz.web.entity.Good;
import jp.co.axiz.web.entity.Recipe;

@Repository
public class PgGoodDao implements GoodDao {
    private static final String SELECT_GOOD = "SELECT recipe_id, user_id, date_part('year', dt) AS year,date_part('month', dt) AS month,date_part('day', dt) AS day FROM good_table "
            + "WHERE " + "recipe_id = :recipeId AND user_id = :userId AND "
            + "date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";
    private static final String INSERT_GOOD = "INSERT INTO good_table (recipe_id, user_id, dt) VALUES (:recipeId, :userId, now())";
    private static final String DELETE_GOOD = "DELETE FROM good_table " + "WHERE "
            + "recipe_id = :recipeId AND user_id = :userId AND "
            + "date_part('year', dt) = :year AND date_part('month', dt) = :month AND date_part('day', dt) = :day";

    private static final String DELETE_GOOD_BY_RECIPEID = "DELETE FROM good_table WHERE recipe_id = :recipeId";
    private static final String DELETE_GOOD_BY_USERID = "DELETE FROM good_table WHERE user_id = :userId";

    @Autowired
    private NamedParameterJdbcTemplate jT;

    @Override
    public Good todaysChecked(Good good) {
        String sql = SELECT_GOOD;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", good.getRecipeId());
        param.addValue("userId", good.getUserId());
        param.addValue("year", good.getYear());
        param.addValue("month", good.getMonth());
        param.addValue("day", good.getDay());
        List<Good> list = jT.query(sql, param, new BeanPropertyRowMapper<Good>(Good.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void insertGood(Good good) {
        String sql = INSERT_GOOD;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", good.getRecipeId());
        param.addValue("userId", good.getUserId());
        jT.update(sql, param);
    }

    @Override
    public void deleteGood(Good good) {
        String sql = DELETE_GOOD;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", good.getRecipeId());
        param.addValue("userId", good.getUserId());
        param.addValue("year", good.getYear());
        param.addValue("month", good.getMonth());
        param.addValue("day", good.getDay());
        jT.update(sql, param);
    }

    @Override
    public void deleteGoodByRecipeId(Integer recipeId) {
        String sql = DELETE_GOOD_BY_RECIPEID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", recipeId);
        jT.update(sql, param);
    }

    @Override
    public void deleteGoodByUserId(Integer userId) {
        String sql = DELETE_GOOD_BY_USERID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        jT.update(sql, param);
    }

}
