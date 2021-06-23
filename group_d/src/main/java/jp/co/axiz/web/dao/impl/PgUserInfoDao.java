package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserInfoDao;
<<<<<<< HEAD
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;

@Repository
public class PgUserInfoDao implements UserInfoDao{

	public static final String INSERT =
			"INSERT INTO user_info (login_name, user_name, password, role_id) VALUES (:login_name, :user_name, :password, 2)";

	public static final String FIND_LOGIN_NAME =
			"SELECT * FROM user_info WHERE login_name = :login_name";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	//新規登録
	@Override
	public void insert(UserInfo user) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_name", user.getLoginName());
		param.addValue("user_name", user.getUserName());
		param.addValue("password", user.getPassword());

		jdbcTemplate.update(INSERT,param);
	}

	//入力されたログインネームがいるかチェック
	@Override
	public boolean findLoginName(String loginName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_name", loginName);


		List<UserInfo> resultList = jdbcTemplate.query(FIND_LOGIN_NAME,param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		if(resultList.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public List<Recipe> newRecipe() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
=======
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
>>>>>>> 7f407d88f7c0a1dd9413d2edbfec8df0bc7a736c
	}
}
