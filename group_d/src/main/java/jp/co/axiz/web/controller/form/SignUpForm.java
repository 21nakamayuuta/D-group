package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpForm{
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String userId;
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	@NotBlank
	private String repass;


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