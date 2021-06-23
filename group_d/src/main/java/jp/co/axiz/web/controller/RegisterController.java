package jp.co.axiz.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.PostForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.util.Images;

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
		System.out.println(categoryList);
		System.out.println(categoryList.get(0).getCategoryName());
		System.out.println(categoryList.get(0).getCategoryId());
		model.addAttribute("categoryList",categoryList);

		return "post";
	}

	@RequestMapping(value="/postInfoCheck", method=RequestMethod.POST)
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





		return "redirect:/top";
	}
}
