package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.AdminService;
import jp.co.axiz.web.service.CategoryService;

@Controller
public class AdminController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String admin(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm,
			Model model) {

		List<UserInfo> userAdminList = adminService.userAll();
		System.out.println(userAdminList.get(0).getPassword());
		model.addAttribute("userAdminList",userAdminList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		List<Search> recipeAllList = adminService.recipeAll();
		System.out.println(recipeAllList.get(0).getRecipeTitle());
		model.addAttribute("recipeAllList",recipeAllList);

		System.out.println(categoryList.size());
		System.out.println(categoryList.get(0).getCategoryName());

		return "admin";
	}

	@RequestMapping("/userDelete")
	public String userDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("userDelete") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm,
			Model model) {

			return "admin";

	}


	@RequestMapping(params="categoryNameSave")
	public String categoryNameSave(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm,
			Model model) {

		return "admin";
	}

	@RequestMapping(params="categoryNameDelete")
	public String categoryNameDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm,
			Model model) {

		return "admin";
	}

	@RequestMapping(params="categoryNameInsert")
	public String categoryNameInsert(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm,
			Model model) {

		return "admin";
	}


}
