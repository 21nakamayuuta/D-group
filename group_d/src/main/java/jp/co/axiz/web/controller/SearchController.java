package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.Search;
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

//	@RequestMapping("/top" )
//	public String top(@ModelAttribute("RecipeSearch") SearchForm form, Model model) {
//		return "top";
//	}

	@RequestMapping("/searchResult")
	public String searchResult(Model model) {
		return "searchResult";
	}

	@RequestMapping(value= "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("RecipeSearch") SearchForm form, Model model) {
		//String seatchInfo  = form.getSearchKeyword();

		//カテゴリの表示
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		//カテゴリIDが入力されたと仮定して、カテゴリ検索が出来るか確認
		System.out.println(searchService.categoryFind(4).get(0).getRecipeTitle());


		if(searchService.find(form.getSearchKeyword()) == null){
			model.addAttribute("message", "一致するレシピは見つかりませんでした。");
		}else {
			List<Search> searchList = searchService.find(form.getSearchKeyword());
			System.out.println(searchList.size());
			model.addAttribute("searchList", searchList);
		}

		model.addAttribute("searchKeyword", form.getSearchKeyword());

		return "searchResult";
	}

	@GetMapping("/recipeSearch")
	public String recipeSearch(@RequestParam(name = "recipeId", required = false) Integer recipeId ,Model model) {


		Integer totalGood = recipeService.totalGood(recipeId);
		List<Recipe> recipeInfo = recipeService.searchRecipeInfo(recipeId);
		List<Food> foodInfo = recipeService.searchFoodInfo(recipeId);
		List<Process> processInfo = recipeService.searchProcessInfo(recipeId);

		model.addAttribute("totalGood",totalGood);
		model.addAttribute("recipeInfo",recipeInfo.get(0));
		model.addAttribute("foodInfo",foodInfo);
		model.addAttribute("categoryInfo",recipeInfo);
		model.addAttribute("processInfo",processInfo);

		return "recipe";

	}
}
