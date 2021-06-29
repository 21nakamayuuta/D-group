package jp.co.axiz.web.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.EditForm;
import jp.co.axiz.web.controller.form.MypageForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.service.impl.PgRecipeService;


@Controller
public class MyPageController {


	@Autowired
	private UserInfoService userService;
	@Autowired
	private PgRecipeService recipeService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpSession session;


	//マイページ遷移
	@RequestMapping(value="/mypage")
	public String mypage(@ModelAttribute("MyPageForm") MypageForm form,@ModelAttribute("RecipeSearch") SearchForm RecipeForm,Model model) {
		UserInfo user= (UserInfo)session.getAttribute("user");
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		List<Recipe> RecipeResult = recipeService.userRecipe(user.getUserId());

		form.setMypageForm(user.getUserName(),user.getPassword());
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("password",user.getPassword());
		model.addAttribute("sumResult", sumResult);
		model.addAttribute("RecipeResult", RecipeResult);
		return "mypage";
	}

	//名前変更
	@RequestMapping(value="/editName",method= RequestMethod.POST)
	public String nameChange(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, @ModelAttribute("RecipeSearch") SearchForm RecipeForm,Model model) {
		//一旦バリデーションを無しでやっています
		/*if (binding.hasErrors()) {
			return "mypage";
		}*/
		UserInfo user= (UserInfo)session.getAttribute("user");


		userService.update_name(form.getMyName(),user.getUserId());

		user.setUserName(form.getMyName());
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		model.addAttribute("sumResult", sumResult);

		return "redirect:mypage";
	}

	//パスワード変更
	@RequestMapping(value="/editPass",method= RequestMethod.POST)
	public String passChange(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, @ModelAttribute("RecipeSearch") SearchForm RecipeForm,Model model) {
		//一旦バリデーションを無しでやっています.
		UserInfo user= (UserInfo)session.getAttribute("user");


		userService.update_pass(form.getMyPass(),user.getUserId());

		user.setPassword(form.getMyPass());
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		model.addAttribute("sumResult", sumResult);

		return "redirect:mypage";
	}

	//レシピ削除
	@RequestMapping(value="/deleteORedit",params="deleteRecipe",method= RequestMethod.POST)
	public String delete(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, @ModelAttribute("RecipeSearch") SearchForm RecipeForm,
			HttpServletRequest req,Model model) {

		String ButtonValue = req.getParameter("deleteRecipe");
		recipeService.deleteRecipe(Integer.parseInt(ButtonValue));

		UserInfo user= (UserInfo)session.getAttribute("user");
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		model.addAttribute("sumResult", sumResult);
		return "redirect:mypage";
	}


	@RequestMapping(value="/deleteORedit",params="editRecipe",method= RequestMethod.POST)
	public String edit(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, @ModelAttribute("RecipeSearch") SearchForm RecipeForm,
			@ModelAttribute("editInfo") EditForm editform,
			HttpServletRequest req,Model model) {

		String ButtonValue = req.getParameter("editRecipe");

		editform.setRecipeId1(Integer.parseInt(ButtonValue));
		Integer recipeId = editform.getRecipeId1();

		List<Recipe> recipeInfo = recipeService.searchRecipeInfo(recipeId);
		List<Food> foodInfo = recipeService.searchFoodInfo(recipeId);
		List<Process> processInfo = recipeService.searchProcessInfo(recipeId);
		List<Category> categoryList = categoryService.searchCategory();
		List<Integer> selectCategory = categoryService.selectCategory(recipeId);

		editform.setFormCategoryId(selectCategory.toArray(new Integer[selectCategory.size()]));
		model.addAttribute("recipeInfo", recipeInfo.get(0));
		session.setAttribute("foodInfo", foodInfo);
		session.setAttribute("processInfo", processInfo);
		model.addAttribute("categoryList", categoryList);
		editform.setOverview(recipeInfo.get(0).getOverview());
		session.setAttribute("oldImage", recipeInfo.get(0).getCompleteImage());

		return "edit";
	}


}
