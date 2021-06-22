package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;


public class IndexController {
	@Autowired
	private RecipeService recipeService;

	@RequestMapping("/top" )
	public String top(Model model) {
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList",recipeList);

		return "top";
	}


}
