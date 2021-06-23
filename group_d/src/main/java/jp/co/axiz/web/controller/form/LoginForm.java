package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;

/*
 * ログイン画面用フォーム
 */
public class LoginForm {

	@NotBlank(message = "IDを入力してください")
	private String loginName;

	@NotBlank(message = "パスワードを入力してください")
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String _loginName) {
		this.loginName = _loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}
}
