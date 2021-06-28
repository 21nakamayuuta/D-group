package jp.co.axiz.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.PostForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.FoodService;
import jp.co.axiz.web.service.PostRecipeService;
import jp.co.axiz.web.service.ProcessService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.util.Images;
import jp.co.axiz.web.util.ParamUtil;

@Controller
public class RegisterController {
	@Autowired
	RecipeService recipeService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	FoodService foodService;

	@Autowired
	ProcessService processService;

	@Autowired
	PostRecipeService postRecipeService;

	@Autowired
	HttpSession session;

	@RequestMapping("/post")
	public String post(@ModelAttribute("postInfo") PostForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, Model model) {
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);

		List<Food> foodList = new ArrayList<Food>();

		session.setAttribute("foodList", foodList);

		List<Process> processList = new ArrayList<Process>();
		session.setAttribute("processList", processList);

		form.setProcessInfoList(new ArrayList<String>());
		return "post";
	}

	@Transactional
	@RequestMapping(value = "/postInfoCheck", params = "register", method = RequestMethod.POST)
	public String postInfoCheck(@Validated @ModelAttribute("postInfo") PostForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, Model model) {
		UserInfo loginUser = (UserInfo) session.getAttribute("user");

		List<Food> foodList = (List<Food>) session.getAttribute("foodList");
		List<Process> processList = (List<Process>) session.getAttribute("processList");

		// 画像保存クラス
		Images imgSave = new Images();
		String imgPath = imgSave.imagePathSave(form.getCompleteImage(), loginUser.getUserId());

		if (binding.hasErrors()) {
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			if (foodList.isEmpty()) {
				model.addAttribute("foodErrorMsg", "材料・分量を追加してください");
			}
			if (processList.isEmpty()) {
				model.addAttribute("processErrorMsg", "作り方を追加してください");
			}
			if (form.getFormCategoryId().length == 0) {
				model.addAttribute("categoryErrorMsg", "カテゴリーを選択してください");
				return "post";
			}
			if (imgPath.equals("noImage")) {
				model.addAttribute("imageError", "画像を選択してください");
			}
			return "post";
		}
		if (foodList.isEmpty() || processList.isEmpty() || form.getFormCategoryId().length == 0
				|| imgPath.equals("noImage")) {
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			if (foodList.isEmpty()) {
				model.addAttribute("foodErrorMsg", "材料・分量を追加してください");
			}
			if (processList.isEmpty()) {
				model.addAttribute("processErrorMsg", "作り方を追加してください");
			}
			if (form.getFormCategoryId().length == 0) {
				model.addAttribute("categoryErrorMsg", "カテゴリーを選択してください");
			}
			if (imgPath.equals("noImage")) {
				model.addAttribute("imageError", "画像を選択してください");
			}
			return "post";
		}
		// 投稿時刻の取得
		Date nowdate = new Date();
		java.sql.Timestamp createTime = new java.sql.Timestamp(nowdate.getTime());

		// recipテーブルに必要な情報を登録
		Recipe InsertRecipe = new Recipe(loginUser.getUserId(), form.getRecipeTitle(), imgPath, form.getCookingTime(),
				form.getOverview(), createTime);
		recipeService.registerRecipe(InsertRecipe);

		// 登録したレシピIDを取得
		Integer newRecipeId = recipeService.searchNewRecipe();

		// カテゴリテーブルに情報を登録
		categoryService.registerRecipeAndCategory(newRecipeId, form.getFormCategoryId());

		// foodテーブルに情報を登録
		foodService.registerFood(form.getFoodNameList(), form.getAmountList(), newRecipeId);

		// processテーブルに情報を登録
		processService.registerProcess(form.getProcessInfoList(), newRecipeId);

		postRecipeService.insertPostRecipe(loginUser.getUserId(), newRecipeId);

		return "redirect:/userTop";

	}

	// food追加
	@RequestMapping(value = "/postInfoCheck", params = "foodAdd", method = RequestMethod.POST)
	public String foodAdd(@ModelAttribute("postInfo") PostForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, Model model) {
		if (form.getAmount().isEmpty() || form.getFoodName().isEmpty()) {
			if (form.getFoodName().isEmpty()) {
				model.addAttribute("nameEmpty", "材料は必須です");
			}
			if (form.getAmount().isEmpty()) {
				model.addAttribute("amountEmpty", "分量は必須です");
			}
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "post";
		}
		if (form.getAmount().length() >= 50 || form.getFoodName().length() >= 50) {
			model.addAttribute("nameEmpty", "50文字以内で入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "post";
		}
		List<Food> foodList = (List<Food>) session.getAttribute("foodList");
		Food newFoodList = new Food(form.getFoodName(), form.getAmount());
		foodList.add(newFoodList);
		session.setAttribute("foodList", foodList);
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		form.setFoodName(null);
		form.setAmount(null);
		return "post";

	}

	// food削除
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/postInfoCheck", params = "foodDel", method = RequestMethod.POST)
	public String foodDel(@ModelAttribute("postInfo") PostForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, HttpServletRequest req, Model model) {
		// 押下されたボタンに応じたところを削除する機能
		String selectButtonValue = req.getParameter("foodDel");
		int value = ParamUtil.checkAndParseInt(selectButtonValue);

		List<Food> foodList = (List<Food>) session.getAttribute("foodList");
		foodList.remove(value);
		session.setAttribute("foodList", foodList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		return "post";
	}

	// process追加
	@RequestMapping(value = "/postInfoCheck", params = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("postInfo") PostForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, Model model) {
		if (form.getProcessDescription().isEmpty()) {
			model.addAttribute("processEmpty", "作り方を入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "post";
		}
		if (form.getProcessDescription().length() >= 50) {
			model.addAttribute("processEmpty", "50文字以内で入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "post";
		}
		List<Process> processList = (List<Process>) session.getAttribute("processList");
		Process newProcessList = new Process(form.getProcessDescription());
		processList.add(newProcessList);
		session.setAttribute("processList", processList);
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		form.setProcessDescription(null);
		return "post";
	}

	// process削除
	@RequestMapping(value = "/postInfoCheck", params = "processDel", method = RequestMethod.POST)
	public String processDel(@ModelAttribute("postInfo") PostForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm, HttpServletRequest req, Model model) {

		// 押下されたボタンに応じたところを削除する機能
		String selectButtonValue = req.getParameter("processDel");
		int value = ParamUtil.checkAndParseInt(selectButtonValue);

		List<Process> processList = (List<Process>) session.getAttribute("processList");
		processList.remove(value);
		session.setAttribute("processList", processList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		return "post";
	}

}
