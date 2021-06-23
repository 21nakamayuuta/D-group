package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;

@Controller
public class IndexController {

	@RequestMapping("/top")
	public String top(@ModelAttribute("loginForm") LoginForm form,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, Model model) {
		//		System.out.println("aaaa");
		return "top";
	}

	//	@RequestMapping("/userTop" )
	//	public String userTop(Model model) {
	//		List<Recipe> recipeList = recipeService.newRecipe();
	//		model.addAttribute("recipeList",recipeList);
	//
	//		return "userTop";
	//	}

}
