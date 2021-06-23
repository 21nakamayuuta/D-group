package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.PostForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.util.Images;

@Controller
public class RegisterController {
	@Autowired
	RecipeService recipeService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/post" )
	public String post(@ModelAttribute ("postInfo") PostForm form,Model model) {
		List<Category> categoryList = categoryService.searchCategory();
		System.out.println(categoryList);
		System.out.println(categoryList.get(0).getCategoryName());
		System.out.println(categoryList.get(0).getCategoryId());
		model.addAttribute("categoryList",categoryList);

		return "post";
	}

	@RequestMapping(value="/postInfoCheck", method=RequestMethod.POST)
	public String postInfoCheck(@ModelAttribute ("postInfo") PostForm form, Model model) {
		System.out.println(form.getRecipeTitle());
		System.out.println(form.getCompleteImage());
		System.out.println(form.getDisplayOrderFood());
		System.out.println(form.getFoodName());
		System.out.println(form.getAmount());
		System.out.println(form.getCookingTime());
		System.out.println(form.getDisplayOrderProcess());
		System.out.println(form.getProcessDescription());
		System.out.println(form.getOverview());
		System.out.println(form.getFormCategoryId());

		Images imgSave = new Images();
		String result = imgSave.imagePathSave(form.getCompleteImage(), "test");


		return "redirect:/top";
	}
}