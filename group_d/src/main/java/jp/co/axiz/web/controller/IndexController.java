package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
import jp.co.axiz.web.form.LoginForm;

@Controller
public class IndexController {

	@RequestMapping("/top")
	public String top(@ModelAttribute("loginForm") LoginForm form, Model model) {
		//		System.out.println("aaaa");
=======
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class IndexController {
	@Autowired
	private RecipeService recipeService;

	@RequestMapping("/top" )
	public String top(@ModelAttribute("RecipeSearch") SearchForm form,Model model) {
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList",recipeList);

>>>>>>> b30b2e0d75a6f8f92537301522f4a00410c57492
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
