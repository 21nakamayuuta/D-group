package jp.co.axiz.web.controller;

<<<<<<< HEAD

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
=======
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 7f407d88f7c0a1dd9413d2edbfec8df0bc7a736c
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

<<<<<<< HEAD
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.SignUpService;


@Controller
public class AuthController {

	@Autowired
	private SignUpService userService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/signUp" ,method = RequestMethod.POST)
	public String signUp(@Validated @ModelAttribute("sign") SignUpForm form ,BindingResult binding ,@ModelAttribute("RecipeSearch") SearchForm Recipeform,Model model) {
		//バリデーション
		if (binding.hasErrors()) {
			model.addAttribute("display", true);
            return "top";
        }

		//入力情報でユーザー作成
		UserInfo user = new UserInfo(form.getUserId(),form.getUserName(),form.getPassword());

		//パスワード一致チェック
		if(!(form.getRepass().equals(form.getPassword()))) {
			String errMsg = messageSource.getMessage("form.lbl.notture", null, Locale.getDefault());
			model.addAttribute("errMsgPASS" ,errMsg);
			model.addAttribute("display", true);
	        return "top";
		}

		//サービスで同じログインネームの有無チェック
		//なければそのままユーザーを登録する
		if(!(userService.INSERT_AND_CHECK(user))) {
			String errMsg = messageSource.getMessage("form.lbl.notUseId", null, Locale.getDefault());
			model.addAttribute("errMsgID" ,errMsg);
			model.addAttribute("display", true);
			return "top";
		}

		//ヘッダーのページ遷移用にセッションにfalse保存
		session.setAttribute("login",false);
		return "userTop";
	}

	@RequestMapping(value="/userTop" )
	public String userTop(@ModelAttribute("signUp") SignUpForm form ,Model model) {
		//ログインしてない状態でユーザートップに来たらトップへ遷移
		if((boolean)session.getAttribute("login")) {
			return "redirect:top";
		}
		return "userTop";
	}



	}

=======
import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserInfoService;

//ログイン処理
@Controller
public class AuthController {

	/*
	 * セッション情報
	 */
	@Autowired
	HttpSession session;

	@Autowired
	private UserInfoService userInfoService;

	/*
	 * ログイン処理 (ログイン画面のログインボタン押下)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@Validated @ModelAttribute("loginForm") LoginForm form,
			BindingResult bindingResult,
			@ModelAttribute("RecipeSearch") SearchForm RecipeForm,
			Model model) {

		//String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

		if (bindingResult.hasErrors()) {
			model.addAttribute("display", true);
			return "top";
		}

		UserInfo user = userInfoService.authentication(form.getLoginName(), form.getPassword());

		if (user == null) {
			// ログイン失敗
			model.addAttribute("display", true);
			model.addAttribute("errMsg", "IDまたはパスワードが一致しません");
			return "top";
		} else {
			// ログイン成功

			// role一覧を取得
			//			List<Role> roleList = roleService.findAll();

			// ログインユーザ情報、role一覧をセッションにセット
			//SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

			//sessionInfo.setLoginUser(user);
			//			sessionInfo.setRoleList(roleList);

			session.setAttribute("user", user);

			return "userTop";
		}
	}

}
>>>>>>> 7f407d88f7c0a1dd9413d2edbfec8df0bc7a736c
