package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SearchService;

@Controller
public class UpdateController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	SearchService searchService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/edit")
	public String recipeEditSearch(@RequestParam(name = "recipeID", required = false) Integer recipeId,
			@ModelAttribute("sign") SignUpForm form,Model model) {

		//recipeIdをもとに情報を取得
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
