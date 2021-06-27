
package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axiz.web.controller.form.LoginForm;
import jp.co.axiz.web.controller.form.SearchForm;
import jp.co.axiz.web.controller.form.SignUpForm;
import jp.co.axiz.web.entity.Category;
import jp.co.axiz.web.entity.Food;
import jp.co.axiz.web.entity.Process;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.Search;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.CategoryService;
import jp.co.axiz.web.service.RecipeService;
import jp.co.axiz.web.service.SearchService;

// @Controller
// @RestController
// @RequestMapping("/search")
public class SearchRestController {
	@Autowired
	RecipeService recipeService;

	@Autowired
	SearchService searchService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpSession session;

	@GetMapping("/keyword/{keyword}")
	public List<Search> keyword(@PathVariable(name = "keyword") String keyword) {
		System.out.println(keyword);
		List<Search> searchList = searchService.find(keyword);

		return searchList;
	}

	@GetMapping("/category")
	public List<Search> categorySearch(@RequestParam(name = "categoryId", required = false) Integer categoryId) {
		List<Search> searchList = searchService.categoryFind(categoryId);
		return searchList;
	}

	// @GetMapping
	// public List<Search> test(@RequestParam(name = "keyword", required = false)
	// String keyword) {
	// System.out.println(keyword);
	// List<Search> searchList = searchService.find(keyword);

	// return searchList;
	// }

}
