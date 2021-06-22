package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class SearchController {
	@Autowired
	RecipeService recipeService;

	@RequestMapping("/searchResult")
	public String searchResult(Model model) {
		return "searchResult";
	}

	@GetMapping("/recipeSearch")
	public String recipeSearch(@RequestParam(name = "recipeId", required = false) Integer recipeId ,Model model) {


		recipeId = 1;


		List<Recipe> recipeInfo = recipeService.searchRecipeInfo(recipeId);
		model.addAttribute("recipeInfo",recipeInfo.get(0));
		model.addAttribute("categoryInfo",recipeInfo);

		return "recipe";
	}
}