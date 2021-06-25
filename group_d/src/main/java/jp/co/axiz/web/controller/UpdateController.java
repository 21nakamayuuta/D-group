package jp.co.axiz.web.controller;

import java.util.Date;

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
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.FoodService;
import jp.co.axiz.web.service.ProcessService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SearchService;
import jp.co.axiz.web.util.Images;

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

		//画像クラス
		Images imgSave = new Images();

		//画像保存クラス
		String imgPath = imgSave.imagePathSave(form.getCompleteImage(), loginUser.getUserId());

		//更新時刻の取得
		Date nowdate = new Date();
		java.sql.Timestamp updateTime = new java.sql.Timestamp(nowdate.getTime());

		Recipe EditRecipe = new Recipe(form.getRecipeTitle(), imgPath, form.getCookingTime(), form.getOverview(), updateTime);
		recipeService.editRecipe(EditRecipe, form.getRecipeId());


		return "redirect:/userTop";

	}
}
