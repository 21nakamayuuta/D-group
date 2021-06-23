package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;

/*
 * マイページ用フォーム
 */
public class MypageForm {

	@NotBlank(message = "IDを入力してください")
	private String name;

	@NotBlank(message = "パスワードを入力してください")
	private String pass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
