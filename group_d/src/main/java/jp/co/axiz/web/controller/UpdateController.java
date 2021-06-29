package jp.co.axiz.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.EditForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.FoodService;
import jp.co.axiz.web.service.ProcessService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SearchService;
import jp.co.axiz.web.util.Images;
import jp.co.axiz.web.util.ParamUtil;

@Controller
public class UpdateController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	SearchService searchService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	FoodService foodService;

	@Autowired
	ProcessService processService;

	@Autowired
	HttpSession session;


	@RequestMapping(value = "/editInfoCheck", params = "register", method = RequestMethod.POST)
	public String editInfoCheck(@Validated @ModelAttribute("editInfo") EditForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			Model model) {

		UserInfo loginUser = (UserInfo) session.getAttribute("user");

		List<Food> foodList = (List<Food>) session.getAttribute("foodInfo");
		List<Process> processList = (List<Process>) session.getAttribute("processInfo");

		if (binding.hasErrors()) {
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			if (foodList.isEmpty()) {
				model.addAttribute("foodErrorMsg", "材料・分量を追加してください");
			}
			if (processList.isEmpty()) {
				model.addAttribute("processErrorMsg", "作り方を追加してください");
			}
			if(form.getFormCategoryId().length == 0) {
				model.addAttribute("categoryErrorMsg", "カテゴリーを選択してください");
			}
			return "edit";
		}
		if (foodList.isEmpty() || processList.isEmpty() || form.getFormCategoryId().length == 0) {
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			if (foodList.isEmpty()) {
				model.addAttribute("foodErrorMsg", "材料・分量を追加してください");
			}
			if (processList.isEmpty()) {
				model.addAttribute("processErrorMsg", "作り方を追加してください");
			}
			if(form.getFormCategoryId().length == 0) {
				model.addAttribute("categoryErrorMsg", "カテゴリーを選択してください");
			}
			return "edit";
		}

		//更新時刻の取得
		Date nowdate = new Date();
		java.sql.Timestamp updateTime = new java.sql.Timestamp(nowdate.getTime());

		//画像クラス
		Images imgSave = new Images();

		//画像保存メソッド
		String imgPath = imgSave.imagePathSave(form.getCompleteImage(), loginUser.getUserId());

		Recipe EditRecipe = null;

		System.out.println(form.getRecipeId1()+"!!!!!!");
		if(!imgPath.equals("noImage")) {
			EditRecipe = new Recipe(form.getRecipeTitle(), imgPath, form.getCookingTime(), form.getOverview(), updateTime);
			recipeService.editRecipe(EditRecipe, form.getRecipeId1());
		}else {
			//recipe情報の更新
			EditRecipe = new Recipe(form.getRecipeTitle(), form.getCookingTime(), form.getOverview(), updateTime);
			recipeService.editNoImageRecipe(EditRecipe, form.getRecipeId1());
		}

		foodService.delAndRegFood(form.getFoodNameList(), form.getAmountList(), form.getRecipeId1());
		processService.delAndRegProcess(form.getProcessInfoList(), form.getRecipeId1());
		categoryService.deleteRecipeAndCategory(form.getRecipeId1(), form.getFormCategoryId());

		return "redirect:/userTop";

	}

	//foodAdd
	@RequestMapping(value = "/editInfoCheck", params = "foodAdd", method = RequestMethod.POST)
	public String foodAdd(@ModelAttribute("editInfo") EditForm form,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			Model model) {
		if (form.getAmount().isEmpty() || form.getFoodName().isEmpty()) {
			if (form.getFoodName().isEmpty()) {
				model.addAttribute("nameEmpty", "材料は必須です");
			}
			if (form.getAmount().isEmpty()) {
				model.addAttribute("amountEmpty", "分量は必須です");
			}
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "edit";
		}
		if (form.getAmount().length() >= 50 || form.getFoodName().length() >= 50) {
			model.addAttribute("nameEmpty", "50文字以内で入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "edit";
		}


		List<Food> foodInfo = (List<Food>) session.getAttribute("foodInfo");
		Food newFoodList = new Food(form.getFoodName(), form.getAmount());
		foodInfo.add(newFoodList);
		session.setAttribute("foodInfo", foodInfo);
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		form.setFoodName(null);
		form.setAmount(null);
		return "edit";
	}


	@RequestMapping(value = "/editInfoCheck", params = "foodDel", method = RequestMethod.POST)
	public String foodDel(@ModelAttribute("editInfo") EditForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			HttpServletRequest req,
			Model model) {
		String selectButtonValue = req.getParameter("foodDel");
		int value = ParamUtil.checkAndParseInt(selectButtonValue);

		List<Food> foodInfo = (List<Food>) session.getAttribute("foodInfo");
		foodInfo.remove(value);
		session.setAttribute("foodInfo", foodInfo);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		return "edit";
	}



	//processAdd
	@RequestMapping(value = "/editInfoCheck", params = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("editInfo") EditForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			Model model) {
		if (form.getProcessDescription().isEmpty()) {
			model.addAttribute("processEmpty", "作り方を入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "edit";
		}
		if (form.getProcessDescription().length() >= 50) {
			model.addAttribute("processEmpty", "50文字以内で入力してください");
			List<Category> categoryList = categoryService.searchCategory();
			model.addAttribute("categoryList", categoryList);
			return "edit";
		}

		List<Process> processInfo = (List<Process>) session.getAttribute("processInfo");
		Process newProcessInfo = new Process(form.getProcessDescription());
		processInfo.add(newProcessInfo);
		session.setAttribute("processInfo", processInfo);
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		form.setProcessDescription(null);
		return "edit";
	}


	@RequestMapping(value = "/editInfoCheck", params = "processDel", method = RequestMethod.POST)
	public String processDel(@ModelAttribute("editInfo") EditForm form, BindingResult binding,
			@ModelAttribute("RecipeSearch") SearchForm SearchKeywordForm,
			HttpServletRequest req,
			Model model) {
		String selectButtonValue = req.getParameter("processDel");
		int value = ParamUtil.checkAndParseInt(selectButtonValue);

		List<Process> processInfo = (List<Process>) session.getAttribute("processInfo");
		processInfo.remove(value);
		session.setAttribute("processInfo", processInfo);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList", categoryList);
		return "edit";
	}
}
