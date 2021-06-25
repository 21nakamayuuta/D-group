package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;

/*
 * マイページ用フォーム
 */
public class MypageForm {

	@NotBlank(message = "空白は出来ません")
	private String myName;

	@NotBlank(message = "空白は出来ません")
	private String myPass;



	public void setMypageForm(String name, String pass) {
		this.myName = name;
		this.myPass = pass;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String name) {
		this.myName = name;
	}

	public String getMyPass() {
		return myPass;
	}

	public void setMyPass(String pass) {
		this.myPass = pass;
	}


}
