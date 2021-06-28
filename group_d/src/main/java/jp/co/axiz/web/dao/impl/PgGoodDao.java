package jp.co.axiz.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.GoodDao;

@Repository
public class PgGoodDao implements GoodDao {

    private static final String INSERT_GOOD = "INSERT INTO good_table (recipe_id, user_id) VALUES (:recipeId, :userId)";
    private static final String DELETE_GOOD = "DELETE FROM good_table WHERE good_id = :goodId";

    @Autowired
    private NamedParameterJdbcTemplate jT;

    @Override
    public void insertGood(Integer recipeId, Integer userId) {
        String sql = INSERT_GOOD;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", recipeId);
        param.addValue("userId", userId);
        jT.update(sql, param);
    }

    @Override
    public void deleteGood(Integer goodId) {
        String sql = DELETE_GOOD;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("goodId", goodId);
        jT.update(sql, param);
    }

}
