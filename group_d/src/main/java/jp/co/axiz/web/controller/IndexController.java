package jp.co.axiz.web.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.RecipeService;

@Controller
public class IndexController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	HttpSession session;

	@RequestMapping("/top" )

		public String top(@ModelAttribute("loginForm") LoginForm loginForm,
				@ModelAttribute("RecipeSearch") SearchForm searchForm,@ModelAttribute("sign") SignUpForm signForm,Model model) {

		//新着レシピ
		List<Recipe> recipeList = recipeService.newRecipe();
		model.addAttribute("recipeList",recipeList);

		//ランキング
		List<Recipe> rankingList = recipeService.ranking();
		model.addAttribute("rankingList",rankingList);

		session.setAttribute("login",true);
		return "top";
	}


	@RequestMapping("/userTop" )
	public String userTop(@ModelAttribute("sign") SignUpForm form ,@ModelAttribute("RecipeSearch") SearchForm searchForm,Model model) {

		//ログインしてない状態でユーザートップに来たらトップへ遷移
				if((boolean)session.getAttribute("login")) {
					return "redirect:top";
				}

		return "userTop";
	}
}




