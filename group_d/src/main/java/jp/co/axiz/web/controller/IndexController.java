package jp.co.axiz.web.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;
=======
import java.util.List;
>>>>>>> 677b7b9dd6e07af69e17099d32f09650258d5043

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
import jp.co.axiz.web.controller.form.SignUpForm;

@Controller
public class IndexController {

	@Autowired
	HttpSession session;

	@RequestMapping(value="/top")
	public String index(@ModelAttribute("signUp") SignUpForm form,Model model) {
		//ヘッダーのページ遷移用にセッションにtrue保存
		session.setAttribute("login",true);
=======
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class IndexController {
	@Autowired
	private RecipeService recipeService;

	@RequestMapping("/top" )
	public String top(Model model) {
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList",recipeList);

>>>>>>> 677b7b9dd6e07af69e17099d32f09650258d5043
		return "top";
	}
	}



