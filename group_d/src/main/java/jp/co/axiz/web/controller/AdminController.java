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
import jp.co.axiz.web.service.GoodService;
import jp.co.axiz.web.service.MadeRecipeService;
import jp.co.axiz.web.service.PostRecipeService;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class AdminController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MadeRecipeService madeRecipeService;
	@Autowired
	private PostRecipeService postRecipeService;

	@Autowired
	private GoodService goodService;

	@RequestMapping("/admin")
	public String admin(@ModelAttribute("sign") SignUpForm form, @ModelAttribute("RecipeSearch") SearchForm searchForm,
			@ModelAttribute("category") SearchForm categoryForm, @ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("categoryEdit") AdminForm adminForm, Model model) {

		List<UserInfo> userAdminList = adminService.userAll();
		model.addAttribute("userAdminList", userAdminList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		List<Search> recipeAllList = adminService.recipeAll();
		model.addAttribute("recipeAllList", recipeAllList);

		return "admin";
	}

	// ?????????????????????
	@RequestMapping(value = "/deleteUser", params = "deleteUserId", method = RequestMethod.POST)
	public String userDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm, @ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm, HttpServletRequest req, Model model) {

		String ButtonValue = req.getParameter("deleteUserId");
		adminService.userDelete(Integer.parseInt(ButtonValue));
		recipeService.deleteRecipeByUserId(Integer.parseInt(ButtonValue));

		madeRecipeService.deleteMadeUserId(Integer.parseInt(ButtonValue));
		postRecipeService.deletePostByUserId(Integer.parseInt(ButtonValue));
		goodService.deleteGoodByUserId(Integer.parseInt(ButtonValue));

		return "redirect:admin";

	}

	// ?????????????????????
	@RequestMapping(value = "/categoryInsert", method = RequestMethod.POST)
	public String categoryNameInsert(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm, @ModelAttribute("adminUser") SearchForm userIdForm,
			@Validated @ModelAttribute("category") AdminForm categoryForm, BindingResult binding,
			@ModelAttribute("categoryEdit") AdminForm adminForm, Model model) {

		// ?????????????????????
		if (binding.hasErrors()) {

		} else {
			categoryService.insertCategory(categoryForm.getCategoryName());
		}

		return "redirect:admin";
	}

	// ?????????????????????
	@RequestMapping(value = "/categoryEdit", params = "categoryNameEdit", method = RequestMethod.POST)
	public String categoryNameSave(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm, @ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm, @ModelAttribute("categoryEdit") AdminForm adminForm,
			HttpServletRequest req, Model model) {

		String ButtonValue = req.getParameter("categoryNameEdit");
		System.out.println(ButtonValue);
		System.out.println(adminForm.getCategoryNameList());
		System.out.println(adminForm.getCategoryIdList());

		// form??????????????????????????????????????????????????????????????????????????????????????????????????????
		// updateCategory??????????????????????????????????????????
		categoryService.updateCategory(Integer.parseInt(ButtonValue), adminForm.getCategoryNameList().get(0));

		return "redirect:admin";
	}

	// ?????????????????????
	@RequestMapping(value = "/categoryEditDelete", params = "categoryNameDelete", method = RequestMethod.POST)
	public String categoryNameDelete(@ModelAttribute("sign") SignUpForm form,
			@ModelAttribute("RecipeSearch") SearchForm searchForm, @ModelAttribute("adminUser") SearchForm userIdForm,
			@ModelAttribute("category") SearchForm categoryForm,
			// @ModelAttribute("categoryEdit") EditForm editForm,
			HttpServletRequest req, Model model) {
		String ButtonValue = req.getParameter("categoryNameDelete");
		System.out.println(ButtonValue);
		categoryService.deleteCategory(Integer.parseInt(ButtonValue));

		return "redirect:admin";
	}

	// ??????????????????
	@RequestMapping(value = "/deleteRecipeAdmin", params = "deleteRecipe", method = RequestMethod.POST)
	public String delete(@Validated @ModelAttribute("MyPageForm") MypageForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm, HttpServletRequest req, Model model) {

		String ButtonValue = req.getParameter("deleteRecipe");
		recipeService.deleteRecipe(Integer.parseInt(ButtonValue));

		return "redirect:admin";
	}

}
