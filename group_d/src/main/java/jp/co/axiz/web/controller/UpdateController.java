package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.controller.form.SignUpForm;

@Controller
public class UpdateController {
	@GetMapping("/edit")
	public String recipeEditSearch(
			@RequestParam(name = "recipeID", required = false) Integer recipeId,
			@ModelAttribute("sign") SignUpForm form,
			Model model) {

		return "";
	}

}
