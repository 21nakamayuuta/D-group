package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.AdminForm;
import jp.co.axiz.web.controller.form.MypageForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.AdminService;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class AdminController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RecipeService recipeService;


	@RequestMapping("/admin")
	public String admin(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm,
			@ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("categoryEdit") AdminForm adminForm,
			Model model) {

		List<UserInfo> userAdminList = adminService.userAll();
		model.addAttribute("userAdminList",userAdminList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		List<Search> recipeAllList = adminService.recipeAll();
		model.addAttribute("recipeAllList",recipeAllList);

		return "admin";
	}

	//ユーザーを削除
	@RequestMapping(value="/deleteUser", params="deleteUserId", method= RequestMethod.POST)
	public String userDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm,
			HttpServletRequest req, Model model) {

		String ButtonValue = req.getParameter("deleteUserId");
		adminService.userDelete(Integer.parseInt(ButtonValue));

		return "redirect:admin";

	}

	//カテゴリの追加
	@RequestMapping(value="/categoryInsert", method= RequestMethod.POST)
	public String categoryNameInsert(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("adminUser") SearchForm userIdForm,
			@Validated @ModelAttribute("category") AdminForm categoryForm,  BindingResult binding,
			@ModelAttribute("categoryEdit") AdminForm adminForm,
			Model model) {

		// バリデーション
	    if (binding.hasErrors()) {

	    }else {
	    	categoryService.insertCategory(categoryForm.getCategoryName());
	    }

		return "redirect:admin";
	}
	//カテゴリの編集
	@RequestMapping(value="/categoryEdit",  params="categoryNameEdit", method=
			RequestMethod.POST)
	public String categoryNameSave(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm,
			@ModelAttribute("categoryEdit") AdminForm adminForm,
			HttpServletRequest req, Model model) {


		String ButtonValue = req.getParameter("categoryNameEdit");
		System.out.println(ButtonValue);
		System.out.println(adminForm.getCategoryNameList());
		System.out.println(adminForm.getCategoryIdList());

		//formで値が取得できない現象が起こっている。これが出来れば何とかなりそう。
		//updateCategoryが使用出来ることを確認した。
		categoryService.updateCategory(Integer.parseInt(ButtonValue), adminForm.getCategoryNameList().get(0));

		return "redirect:admin";
	}

	//カテゴリの削除
	@RequestMapping(value="/categoryEditDelete", params="categoryNameDelete", method= RequestMethod.POST)
	public String categoryNameDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm,
			//@ModelAttribute("categoryEdit") EditForm editForm,
			HttpServletRequest req, Model model) {
		String ButtonValue = req.getParameter("categoryNameDelete");
		System.out.println(ButtonValue);
		categoryService.deleteCategory(Integer.parseInt(ButtonValue));

		return "redirect:admin";
	}

	//レシピの削除
	@RequestMapping(value="/deleteRecipeAdmin", params="deleteRecipe",method= RequestMethod.POST)
	public String delete(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, @ModelAttribute("RecipeSearch") SearchForm RecipeForm,
			HttpServletRequest req,Model model) {

		String ButtonValue = req.getParameter("deleteRecipe");
		recipeService.deleteRecipe(Integer.parseInt(ButtonValue));

		return "redirect:admin";
	}



}
