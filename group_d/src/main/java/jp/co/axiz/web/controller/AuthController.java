package jp.co.axiz.web.controller;

import java.util.List;
import java.util.Locale;

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

@Controller
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

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@Validated @ModelAttribute("sign") SignUpForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm Recipeform, @ModelAttribute("loginForm") LoginForm loginForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {
		// バリデーション
		if (binding.hasErrors()) {
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// //新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);
			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);

			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);

			return "top";
		}

		// 入力情報でユーザー作成
		UserInfo user = new UserInfo(form.getUserId(), form.getUserName(), form.getPassword());

		// パスワード一致チェック
		if (!(form.getRepass().equals(form.getPassword()))) {
			String errMsg = messageSource.getMessage("form.lbl.notture", null, Locale.getDefault());
			model.addAttribute("errMsgPASS", errMsg);
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// //新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);
			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);

			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);

			return "top";
		}

		// サービスで同じログインネームの有無チェック
		// なければそのままユーザーを登録する
		if (!(userService.INSERT_AND_CHECK(user))) {
			String errMsg = messageSource.getMessage("form.lbl.notUseId", null, Locale.getDefault());
			model.addAttribute("errMsgID", errMsg);
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);

			// //新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);
			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);
			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);

			return "top";
		}

		// ヘッダーのページ遷移用にセッションにfalse保存
		session.setAttribute("login", false);
		return "userTop";
	}

	// ログイン処理 (ログイン画面のログインボタン押下)

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, @ModelAttribute("sign") SignUpForm signUpForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		// String errMsg = messageSource.getMessage("login.error", null,
		// Locale.getDefault());

		if (bindingResult.hasErrors()) {
			model.addAttribute("display", true);
			model.addAttribute("LoginDisplay", true);
			// //新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);
			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);
			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);
			return "top";
		}

		UserInfo user = userInfoService.authentication(form.getLoginName(), form.getPassword());

		if (user == null) {
			// ログイン失敗
			model.addAttribute("display", true);
			model.addAttribute("LoginDisplay", true);
			model.addAttribute("errMsg", "IDまたはパスワードが一致しません");
			// 新着レシピ
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);
			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);
			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);
			return "top";
		} else {
			// ログイン成功

			// role一覧を取得
			// List<Role> roleList = roleService.findAll();

			// ログインユーザ情報、role一覧をセッションにセット
			// SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

			// sessionInfo.setLoginUser(user);
			// sessionInfo.setRoleList(roleList);
			// List<Recipe> recipeList = recipeService.newRecipe();
			// model.addAttribute("recipeList", recipeList);

			// //ランキング
			// List<Recipe> rankingList = recipeService.ranking();
			// model.addAttribute("rankingList", rankingList);

			// //カテゴリの表示
			// List<Category> categoryList = categoryService.searchCategory();
			// model.addAttribute("categoryList", categoryList);

			session.setAttribute("user", user);
			session.setAttribute("login", false);
			return "userTop";
		}
	}

	/*
	 * ログアウト
	 */
	@RequestMapping(value = "/top", method = RequestMethod.POST)
	public String logout(@ModelAttribute("loginForm") LoginForm form,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, @ModelAttribute("sign") SignUpForm signUpForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		// 新着レシピ
		// List<Recipe> recipeList = recipeService.newRecipe();
		// model.addAttribute("recipeList", recipeList);
		// //ランキング
		// List<Recipe> rankingList = recipeService.ranking();
		// model.addAttribute("rankingList", rankingList);

		// //カテゴリの表示
		// List<Category> categoryList = categoryService.searchCategory();
		// model.addAttribute("categoryList", categoryList);

		session.invalidate();
		return "top";
	}

}
