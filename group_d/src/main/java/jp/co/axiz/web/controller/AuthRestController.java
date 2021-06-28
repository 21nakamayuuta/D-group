package jp.co.axiz.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.SignUpService;
import jp.co.axiz.web.service.UserInfoService;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SignUpService singUpService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpSession session;

	// 新規登録
	@PostMapping("/signUp")
	public Map<String, Object> signUp(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "userName", required = false) String userName,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "repass", required = false) String repass,
			@Validated @ModelAttribute("sign") SignUpForm form, BindingResult binding) {

		form.setUserId(userId);
		form.setUserName(userName);
		form.setPassword(password);
		form.setRepass(repass);
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> errMsg = new HashMap<>();
		// 入力情報でユーザー作成
		UserInfo user = new UserInfo(form.getUserId(), form.getUserName(), form.getPassword());
		for (FieldError fieldError : binding.getFieldErrors()) {
			if (binding.hasFieldErrors(fieldError.getField())) {
				errMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}

		// パスワード一致チェック
		if (!form.getRepass().equals(form.getPassword())) {
			errMsg.put("errNotPassMatch", "パスワードが一致しません。");
		}

		// サービスで同じログインネームの有無チェック
		// なければそのままユーザーを登録する
		if (singUpService.INSERT_AND_CHECK(user)) {
			errMsg.put("errNotUseId", "このIDは使用出来ません。");
		}

		if (errMsg.isEmpty()) {
			singUpService.signUp(user);
			UserInfo loginUser = userInfoService.authentication(user.getLoginName(), user.getPassword());
			session.setAttribute("login", false);
			session.setAttribute("user", loginUser);
		}
		map.put("errMsg", errMsg);
		return map;
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam(name = "loginName", required = false) String loginName,
			@RequestParam(name = "password", required = false) String password,
			@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult binding) {
		form.setLoginName(loginName);
		form.setPassword(password);
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> errMsg = new HashMap<>();
		for (FieldError fieldError : binding.getFieldErrors()) {
			if (binding.hasFieldErrors(fieldError.getField())) {
				errMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		System.out.println(loginName + ", " + password + " : " + form.getLoginName() + ", " + form.getPassword());
		UserInfo user = userInfoService.authentication(form.getLoginName(), form.getPassword());

		if (!binding.hasErrors() && user == null) {
			errMsg.put("errNotUserIdOrPass", "IDまたはパスワードが一致しません");
		} else {

			session.setAttribute("user", user);
			session.setAttribute("login", false);
		}
		map.put("errMsg", errMsg);
		return map;
	}

	@PostMapping("/logout")
	public void logout() {
		session.invalidate();
	}

}
