package jp.co.axiz.web.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.PostRecipe;
import jp.co.axiz.web.entity.MadeRecipe;

import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.service.PostRecipeService;
import jp.co.axiz.web.service.MadeRecipeService;

import jp.co.axiz.web.controller.form.SearchForm;

// @Controller
@RestController
@RequestMapping("/userTop")
public class UserTopController {
    @Autowired
    HttpSession session;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PostRecipeService postRecipeService;
    @Autowired
    private MadeRecipeService madeRecipeService;

    @GetMapping("/getRecipe")
    @ResponseBody
    public Map<String, Object> getMadePostHistory(
            @RequestParam(name = "year", required = false, defaultValue = "2021") Integer year,
            @RequestParam(name = "month", required = false, defaultValue = "5") Integer month,
            @RequestParam(name = "day", required = false, defaultValue = "23") Integer day,
            @ModelAttribute("RecipeSearch") SearchForm RecipeForm) {

        UserInfo user = (UserInfo) session.getAttribute("user");
        System.out.println(user.getUserId() + ", " + year + ", " + month + ", " + day);
        // 投稿したレシピを表示
        PostRecipe postRecipe = new PostRecipe(user.getUserId(), year, month, day);
        List<PostRecipe> postRecipeList = postRecipeService.getPostRecipe(postRecipe);

        for (PostRecipe pr : postRecipeList) {
            pr.getAllData();
        }
        // 投稿したレシピを表示
        MadeRecipe madeRecipe = new MadeRecipe(user.getUserId(), year, month, day);
        List<MadeRecipe> madeRecipeList = madeRecipeService.getMadeRecipe(madeRecipe);
        for (MadeRecipe mr : madeRecipeList) {
            mr.getAllData();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("postRecipe", postRecipeList);
        map.put("madeRecipe", madeRecipeList);
        return map;
    }

    @GetMapping("/getPostMadeDate")
    @ResponseBody
    public Map<String, Object> getPostMadeDate() {
        UserInfo user = (UserInfo) session.getAttribute("user");
        List<PostRecipe> postRecipeList = postRecipeService.getAllPostRecipe(user.getUserId());
        List<MadeRecipe> madeRecipeList = madeRecipeService.getAllMadeRecipe(user.getUserId());
        System.out.println(user.getUserId());
        System.out.println(postRecipeList);
        System.out.println(madeRecipeList);
        Map<String, Object> map = new HashMap<>();
        map.put("postRecipe", postRecipeList);
        map.put("madeRecipe", madeRecipeList);
        return map;
    }
}
