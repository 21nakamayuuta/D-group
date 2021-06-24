package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;

//User_infoテーブル用のDAO

@Repository
public class PgUserInfoDao implements UserInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_LOGIN_NAME = "SELECT user_id, login_name, user_name, password, role_id FROM user_info WHERE login_name = :loginName";
	private static final String SELECT_LOGIN_NAME_AND_PASS = "SELECT user_id, login_name, user_name, password, role_id FROM user_info WHERE login_name = :loginName AND password = :password";

	public static final String INSERT =
			"INSERT INTO user_info (login_name, user_name, password, role_id) VALUES (:login_name, :user_name, :password, 2)";

	public static final String FIND_LOGIN_NAME =
			"SELECT * FROM user_info WHERE login_name = :login_name";
	public static final String UPDATE_USER_NAME = "UPDATE user_info SET user_name = :user_name WHERE user_id =:user_id";

	public static final String UPDATE_USER_PASS = "UPDATE user_info SET password = :password WHERE user_id =:user_id";

	//新規登録
	@Override
	public void insert(UserInfo user) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_name", user.getLoginName());
		param.addValue("user_name", user.getUserName());
		param.addValue("password", user.getPassword());

		jdbcTemplate.update(INSERT,param);
	}
	//名前変更
		@Override
		public void update_name(String name,Integer user_id) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("user_name", name);
			param.addValue("user_id", user_id);

			jdbcTemplate.update(UPDATE_USER_NAME,param);
		}
	//パスワード変更
		@Override
		public void update_pass(String pass, Integer user_id) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("password", pass);
			param.addValue("user_id", user_id);

			jdbcTemplate.update(UPDATE_USER_PASS,param);

		}

	//入力されたログインネームがいるかチェック
	@Override
	public boolean isFindLoginName(String loginName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_name", loginName);


		List<UserInfo> resultList = jdbcTemplate.query(FIND_LOGIN_NAME,param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		if(resultList.isEmpty()) {
			return false;
		}

		return true;
	}


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

	@Override
	public List<Recipe> newRecipe() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
