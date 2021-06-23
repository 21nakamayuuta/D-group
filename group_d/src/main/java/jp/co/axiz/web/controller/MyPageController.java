package jp.co.axiz.web.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SignUpService;
import jp.co.axiz.web.service.UserInfoService;


@Controller
public class MyPageController {


	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SignUpService userService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/mypage")
	public String mypage(Model model) {
		UserInfo user= (UserInfo)session.getAttribute("user");
		Recipe sumResult = recipeService.totalRecipe(user.getUserId());

		model.addAttribute("user",user);
		model.addAttribute("sumResult", sumResult);

		return "mypage";
	}
	/*@RequestMapping(value="/signUp" ,method = RequestMethod.POST)
	public String signUp(@Validated @ModelAttribute("sign") SignUpForm form ,BindingResult binding ,
			@ModelAttribute("RecipeSearch") SearchForm Recipeform,
			@ModelAttribute("loginForm") LoginForm loginForm,Model model) {
		//バリデーション
		if (binding.hasErrors()) {
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);
	        return "top";
	    }

		//入力情報でユーザー作成
		UserInfo user = new UserInfo(form.getUserId(),form.getUserName(),form.getPassword());

		//パスワード一致チェック
		if(!(form.getRepass().equals(form.getPassword()))) {
			String errMsg = messageSource.getMessage("form.lbl.notture", null, Locale.getDefault());
			model.addAttribute("errMsgPASS" ,errMsg);
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);
	        return "top";
		}

		//サービスで同じログインネームの有無チェック
		//なければそのままユーザーを登録する
		if(!(userService.INSERT_AND_CHECK(user))) {
			String errMsg = messageSource.getMessage("form.lbl.notUseId", null, Locale.getDefault());
			model.addAttribute("errMsgID" ,errMsg);
			model.addAttribute("SignUpDisplay", true);
			model.addAttribute("display", true);
			return "top";
		}

		//ヘッダーのページ遷移用にセッションにfalse保存
		session.setAttribute("login",false);
		return "userTop";
	}


	  //ログイン処理 (ログイン画面のログインボタン押下)

	*/

}
