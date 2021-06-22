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

	@RequestMapping(value= "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("RecipeSearch") SearchForm form, Model model) {
		//String seatchInfo  = form.getSearchKeyword();

		if(searchService.find(form.getSearchKeyword()) == null){
			model.addAttribute("message", "一致するレシピは見つかりませんでした。");
		}else {
			List<Search> searchList = searchService.find(form.getSearchKeyword());
			System.out.println(searchList.size());
			model.addAttribute("searchList", searchList);
		}

		model.addAttribute("searchKeyword", form.getSearchKeyword());

		return "searchResult";
	}
}
