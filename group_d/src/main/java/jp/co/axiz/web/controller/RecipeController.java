package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
	@RequestMapping("/searchResult")
	public String searchResult(Model model) {
		return "searchResult";
	}

	@RequestMapping("/recipeSearch")
	public String recipeSearch(Model model) {


		return "recipe";
	}
}
