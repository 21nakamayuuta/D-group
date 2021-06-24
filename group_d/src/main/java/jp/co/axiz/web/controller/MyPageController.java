package jp.co.axiz.web.controller;



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

import jp.co.axiz.web.controller.form.MypageForm;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
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
	HttpSession session;

	@RequestMapping(value="/mypage")
	public String mypage(@ModelAttribute("MyPageForm") MypageForm form,Model model) {
		UserInfo user= (UserInfo)session.getAttribute("user");
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());

		form.setMypageForm(user.getUserName(),user.getPassword());
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("password",user.getPassword());
		model.addAttribute("sumResult", sumResult);
		return "mypage";
	}

	@RequestMapping(value="/editName",method= RequestMethod.POST)
	public String nameChange(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, Model model) {
		//一旦バリデーションを無しでやっています
		UserInfo user= (UserInfo)session.getAttribute("user");


		userService.update_name(form.getMyName(),user.getUserId());
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		model.addAttribute("sumResult", sumResult);

		return "mypage";
	}

	@RequestMapping(value="/editPass",method= RequestMethod.POST)
	public String passChange(@Validated @ModelAttribute("MyPageForm") MypageForm form,
			BindingResult binding, Model model) {
		//一旦バリデーションを無しでやっています.
		UserInfo user= (UserInfo)session.getAttribute("user");


		userService.update_name(form.getMyPass(),user.getUserId());
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());
		model.addAttribute("sumResult", sumResult);

		return "mypage";
	}


}
