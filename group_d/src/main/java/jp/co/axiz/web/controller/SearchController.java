package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;

	@RequestMapping("/top" )
	public String top(@ModelAttribute("RecipeSearch") SearchForm form, Model model) {
		return "top";
	}

	@RequestMapping(value= "/searchResult", method = RequestMethod.POST)
	public String search(@ModelAttribute("RecipeSearch") SearchForm form, Model model) {
		//String seatchInfo  = form.getSearchKeyword();
		List<Search> searchList = searchService.find(form.getSearchKeyword());
		System.out.println(searchList.get(0).getRecipeId());
		return "searchResult";
	}
}
