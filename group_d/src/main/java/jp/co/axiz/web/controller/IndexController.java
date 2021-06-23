package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class IndexController {

	@Autowired
	RecipeService recipeService;


	@RequestMapping("/top")
	public String top(@ModelAttribute("loginForm") LoginForm form,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, Model model) {
		//		System.out.println("aaaa");
		return "top";
	}


	@RequestMapping("/userTop" )
	public String userTop(Model model) {
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList",recipeList);

		return "userTop";
	}



}
