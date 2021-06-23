package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;

//User_infoテーブル用のDAO

@Repository
public class PgUserInfoDao implements UserInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_LOGIN_NAME = "SELECT user_id, login_name, user_name, password, role_id FROM user_info WHERE login_name = :loginName";
	private static final String SELECT_LOGIN_NAME_AND_PASS = "SELECT user_id, login_name, user_name, password, role_id FROM user_info WHERE login_name = :loginName AND password = :password";

	/**
	 * user_idによる検索
	 */
	@Override
	public UserInfo findLoginName(String loginName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginName", loginName);

		List<UserInfo> resultList = jdbcTemplate.query(SELECT_LOGIN_NAME, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return resultList.isEmpty() ? null : resultList.get(0);
	}

	/**
	 * user_id、passwordによる検索
	 */
	@Override
	public UserInfo findLoginNameAndPassword(String loginName, String password) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginName", loginName);
		param.addValue("password", password);

		List<UserInfo> resultList = jdbcTemplate.query(SELECT_LOGIN_NAME_AND_PASS, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return resultList.isEmpty() ? null : resultList.get(0);
	}
}
