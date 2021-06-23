package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
