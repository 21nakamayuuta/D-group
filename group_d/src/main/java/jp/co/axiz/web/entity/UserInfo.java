package jp.co.axiz.web.entity;

public class UserInfo {
	//table情報
	private Integer userId;
	private String loginName;
	private String userName;
	private String password;
	private Integer roleId;


	//SQLで取得する際に必要になりそうな情報は随時追加
	public UserInfo() {
	}


	public UserInfo(String login_name, String user_name, String password) {
		this.loginName = login_name;
		this.userName = user_name;
		this.password = password;
	}


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}




}
