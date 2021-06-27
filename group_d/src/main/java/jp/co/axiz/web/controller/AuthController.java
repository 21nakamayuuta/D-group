package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class AuthController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SignUpService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpSession session;

	// 新規登録
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@Validated @ModelAttribute("sign") SignUpForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm Recipeform, @ModelAttribute("loginForm") LoginForm loginForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model, HttpServletRequest request) {
		// バリデーション
		if (binding.hasErrors()) {
			// System.out.println(binding.getFieldError("userId").getDefaultMessage());
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);

			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);

			return loginForm.getPageName();
			// return "top";
		}

		// 入力情報でユーザー作成
		UserInfo user = new UserInfo(form.getUserId(), form.getUserName(), form.getPassword());

		// パスワード一致チェック
		if (!(form.getRepass().equals(form.getPassword()))) {
			model.addAttribute("errMsgPASS", "パスワードが一致しません。");
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);

			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);

			return loginForm.getPageName();
			// return "top";
		}

		// サービスで同じログインネームの有無チェック
		// なければそのままユーザーを登録する
		if (!(userService.INSERT_AND_CHECK(user))) {
			model.addAttribute("errMsgID", "このIDは使用出来ません。");
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);
			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);

			// return "top";
			return loginForm.getPageName();
		} else {
			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);
			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
		}

		// ヘッダーのページ遷移用にセッションにfalse保存
		UserInfo loginUser = userInfoService.authentication(user.getLoginName(), user.getPassword());
		session.setAttribute("login", false);
		session.setAttribute("user", loginUser);

		return "redirect:userTop";
	}

	// ログイン処理 (ログイン画面のログインボタン押下)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, @ModelAttribute("sign") SignUpForm signUpForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("display", true);
			model.addAttribute("LoginDisplay", true);
			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);
			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			// return "top";
			System.out.println(signUpForm.getPageName());
			return signUpForm.getPageName();
		}

		UserInfo user = userInfoService.authentication(form.getLoginName(), form.getPassword());

		if (user == null) {
			// ログイン失敗
			model.addAttribute("display", true);
			model.addAttribute("LoginDisplay", true);
			model.addAttribute("errMsg", "IDまたはパスワードが一致しません");
			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);
			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);
			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			// return "top";
			return signUpForm.getPageName();
		} else {
			// ログイン成功

			// 新着レシピ
			List<Recipe> recipeList = recipeService.newRecipe();
			model.addAttribute("recipeList", recipeList);

			// ランキング
			List<Recipe> rankingList = recipeService.ranking();
			model.addAttribute("rankingList", rankingList);

			// カテゴリの表示
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);

			session.setAttribute("user", user);
			session.setAttribute("login", false);
			return "redirect:userTop";
		}
	}

	/*
	 * ログアウト
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(@ModelAttribute("loginForm") LoginForm form,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, @ModelAttribute("sign") SignUpForm signUpForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		// 新着レシピ
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList", recipeList);
		// ランキング
		List<Recipe> rankingList = recipeService.ranking();
		model.addAttribute("rankingList", rankingList);

		// カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		session.invalidate();
		return "redirect:top";
	}

}
