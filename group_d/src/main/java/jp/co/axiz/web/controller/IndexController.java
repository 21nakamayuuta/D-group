package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class IndexController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	HttpSession session;

	@RequestMapping("/top")
	public String top(@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		// ランキング
		List<Recipe> rankingList = recipeService.ranking();
		model.addAttribute("rankingList", rankingList);

		// 新着レシピ
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList", recipeList);

		// カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		// ユーザーがログインしている場合はユーザートップに遷移させる
		if (session.getAttribute("user") != null) {
			UserInfo user = (UserInfo) session.getAttribute("user");
			System.out.println(user.getUserId() + ", " + user.getUserName() + ", " + user.getRoleId());
			return "redirect:userTop";
		} else {
			session.setAttribute("login", true);
			return "top";
		}
	}

	@RequestMapping("/userTop")
	public String userTop(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("categorySearch") SearchForm categoryForm, Model model) {

		// ログインしてない状態でユーザートップに来たらトップへ遷移
		if (session.getAttribute("user") == null) {
			return "redirect:top";
		}

		// 新着レシピ
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList", recipeList);

		// ランキング
		List<Recipe> rankingList = recipeService.ranking();
		model.addAttribute("rankingList", rankingList);

		// カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		return "userTop";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("RecipeSearch") SearchForm searchForm) {
		return "edit";
	}

	//@RequestMapping("/admin")
	public String admin(@ModelAttribute("RecipeSearch") SearchForm searchForm) {
		return "admin";
	}

	@RequestMapping("/search")
	public String searchResult(@ModelAttribute("categorySearch") SearchForm categoryForm, Model model) {
		// カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		return "searchResult";
	}
}
