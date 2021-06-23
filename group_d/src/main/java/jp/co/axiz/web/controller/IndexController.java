package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.form.LoginForm;

@Controller
public class IndexController {

	@RequestMapping("/top")
	public String top(@ModelAttribute("loginForm") LoginForm form, Model model) {
		//		System.out.println("aaaa");
		return "top";
	}
}
