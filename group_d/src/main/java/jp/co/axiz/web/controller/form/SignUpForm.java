package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm {
	@NotBlank(message = "IDを入力してください")
	@Size(max = 20, message = "IDは20文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "半角英数字のみ入力してください")
	private String userId;
	@NotBlank(message = "名前を入力してください")
	@Size(max = 20, message = "名前は20文字以内で入力してください")
	private String userName;
	@NotBlank(message = "パスワードを入力してください")
	@Size(max = 20, message = "パスワードは20文字以内で入力してください")
	private String password;
	@NotBlank(message = "パスワードを入力してください")
	private String repass;

	/*
	 * 数値を入力してください 半角英数字のみ入力してください このIDは、使用できません パスワードが一致しません
	 */

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

}