package jp.co.axiz.web.controller;

import java.sql.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SignUpService;
import jp.co.axiz.web.service.UserInfoService;

// @Controller
@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SignUpService singUpService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpSession session;

	// 新規登録
	// @RequestMapping(value = "/signUp", method = RequestMethod.POST)
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
				// System.out.println(fieldError.getCodes()[] + ", " + );
				errMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}

		// パスワード一致チェック
		if (!form.getRepass().equals(form.getPassword())) {
			errMsg.put("errNotPassMatch", "パスワードが一致しません。");
			// return map;
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
		// return "redirect:userTop";
		map.put("errMsg", errMsg);
		return map;
	}

	// ログイン処理 (ログイン画面のログインボタン押下)
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam(name = "loginName", required = false) String loginName,
			@RequestParam(name = "password", required = false) String password,
			@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult binding) {
		form.setLoginName(loginName);
		form.setPassword(password);
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> errMsg = new HashMap<>();
		// 入力情報でユーザー作成
		// UserInfo user = new UserInfo(form.getLoginName(), form.getPassword());
		// System.out.println(binding.getFieldError("loginName"));
		// System.out.println(binding.getFieldErrors("LoginName"));
		for (FieldError fieldError : binding.getFieldErrors()) {
			// System.out.println(fieldError.getField());
			if (binding.hasFieldErrors(fieldError.getField())) {
				errMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		System.out.println(loginName + ", " + password + " : " + form.getLoginName() + ", " + form.getPassword());
		UserInfo user = userInfoService.authentication(form.getLoginName(), form.getPassword());

		if (!binding.hasErrors() && user == null) {
			errMsg.put("errNotUserIdOrPass", "IDまたはパスワードが一致しません");
		} else {
			// ログイン成功

			// // 新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);

			// // ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);

			// // カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);

			session.setAttribute("user", user);
			session.setAttribute("login", false);
		}
		map.put("errMsg", errMsg);
		return map;
	}

	/*
	 * ログアウト
	 */
	// @RequestMapping(value = "/logout", method = RequestMethod.POST)
	@PostMapping("/logout")
	public void logout() {
		session.invalidate();
	}

}
