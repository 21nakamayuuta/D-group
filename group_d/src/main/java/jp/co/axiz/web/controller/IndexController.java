package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.controller.form.SignUpForm;

@Controller
public class IndexController {

	@Autowired
	HttpSession session;

	@RequestMapping(value="/top")
	public String index(@ModelAttribute("signUp") SignUpForm form,Model model) {
		//ヘッダーのページ遷移用にセッションにtrue保存
		session.setAttribute("login",true);
		return "top";
	}
	}



