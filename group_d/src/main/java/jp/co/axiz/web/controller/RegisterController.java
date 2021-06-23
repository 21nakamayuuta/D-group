package jp.co.axiz.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.PostForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
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
	HttpSession session;

	@RequestMapping("/post" )
	public String post(@ModelAttribute ("postInfo") PostForm form,Model model) {
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList",categoryList);

		List<Food> foodList = new ArrayList<Food>();
		session.setAttribute("foodList",foodList);
		return "post";
	}

	@RequestMapping(value="/postInfoCheck",params="register", method=RequestMethod.POST)
	public String postInfoCheck(@ModelAttribute ("postInfo") PostForm form, Model model) {
		UserInfo loginUser = (UserInfo) session.getAttribute("user");

		//画像保存クラス
		Images imgSave = new Images();
		String imgPath = imgSave.imagePathSave(form.getCompleteImage(), loginUser.getUserId());

		//投稿時刻の取得
		Date nowdate = new Date();
		java.sql.Timestamp createTime = new java.sql.Timestamp(nowdate.getTime());

//		Recipe InsertRecipe = new Recipe(loginUser.getUserId(), form.getRecipeTitle(), imgPath, form.getCookingTime(), form.getOverview(), createTime);
//		recipeService.registerRecipe(InsertRecipe);
		Integer newRecipeId = recipeService.searchNewRecipe();
//		categoryService.registerRecipeAndCategory(newRecipeId, form.getFormCategoryId());





		return "redirect:/userTop";
	}

	@RequestMapping(value="/postInfoCheck",params="foodAdd", method=RequestMethod.POST)
	public String foodAdd(@ModelAttribute ("postInfo") PostForm form, Model model) {
		List<Food> foodList = (List<Food>) session.getAttribute("foodList");
		Food newFoodList = new Food(form.getFoodName(), form.getAmount());
		foodList.add(newFoodList);
		session.setAttribute("foodList", foodList);
		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList",categoryList);
		return "post";
	}

	@RequestMapping(value="/postInfoCheck",params="foodDel", method=RequestMethod.POST)
	public String foodDel(@ModelAttribute ("postInfo") PostForm form,HttpServletRequest req, Model model) {
		String selectButtonValue = req.getParameter("foodDel");

		System.out.println(selectButtonValue);
		Integer value = ParamUtil.checkAndParseInt(selectButtonValue);
		List<Food> foodList = (List<Food>) session.getAttribute("foodList");
		foodList.remove(0);
		session.setAttribute("foodList", foodList);

		List<Category> categoryList = categoryService.searchCategory();
		model.addAttribute("categoryList",categoryList);
		return "post";
	}

}
