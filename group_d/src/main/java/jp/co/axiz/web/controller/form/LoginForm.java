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

	private String pageName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
