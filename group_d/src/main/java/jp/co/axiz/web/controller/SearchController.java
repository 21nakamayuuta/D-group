
package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	RecipeService recipeService;

	@Autowired
	SearchService searchService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpSession session;

	@RequestMapping("/searchResult")
	public String searchResult(
			Model model) {
		return "searchResult";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			@ModelAttribute("sign") SignUpForm form, @ModelAttribute("loginForm") LoginForm loginform,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm, Model model) {

		//カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		//検索の処理
		if (searchService.find(SearchKeywordForm.getSearchKeyword()) == null) {
			model.addAttribute("message", "一致するレシピは見つかりませんでした。");
		} else {
			List<Search> searchList = searchService.find(SearchKeywordForm.getSearchKeyword());
			System.out.println(searchList.size());
			model.addAttribute("searchList", searchList);

			//List<Search> sbList = searchList.subList(0,3);
			//model.addAttribute("searchList", sbList);

		}

		model.addAttribute("searchKeyword", SearchKeywordForm.getSearchKeyword());

		int roleId = 0;
		UserInfo user = (UserInfo) session.getAttribute("user");

		if (user == null) {
			roleId = 0;
		} else {
			roleId = user.getRoleId();
		}

		model.addAttribute("roleId", roleId);

		return "searchResult";
	}


	@RequestMapping(value = "/categorySearch", method = RequestMethod.GET)
	public String categorySearch(
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			@ModelAttribute("categorySearch") SearchForm categorySearchForm,
			@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("loginForm") LoginForm loginform,
			Model model) {

		//カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);



		//カテゴリ検索の処理
		if (searchService.categoryFind(categorySearchForm.getCategoryId()) == null) {
			model.addAttribute("message", "一致するレシピは見つかりませんでした。");
		} else {
			List<Search> searchList = searchService.categoryFind(categorySearchForm.getCategoryId());
			model.addAttribute("searchList", searchList);
		}

		//レシピ一覧表示の”○○のレシピ”用
		model.addAttribute("searchKeyword", categorySearchForm.getCategoryName());

		return "searchResult";
	}

	@GetMapping("/recipe")
	public String recipeSearch(
			@RequestParam(name = "recipeID", required = false) Integer recipeId,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("loginForm") LoginForm loginform,
			Model model) {

		Integer totalGood = recipeService.totalGood(recipeId);
		List<Recipe> recipeInfo = recipeService.searchRecipeInfo(recipeId);
		List<Food> foodInfo = recipeService.searchFoodInfo(recipeId);
		List<Process> processInfo = recipeService.searchProcessInfo(recipeId);

		model.addAttribute("totalGood", totalGood);
		model.addAttribute("recipeInfo", recipeInfo.get(0));
		model.addAttribute("foodInfo", foodInfo);
		model.addAttribute("categoryInfo", recipeInfo);
		model.addAttribute("processInfo", processInfo);

		return "recipe";

	}
}
