package jp.co.axiz.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axiz.web.entity.Good;
import jp.co.axiz.web.entity.MadeRecipe;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.GoodService;
import jp.co.axiz.web.service.MadeRecipeService;
import jp.co.axiz.web.service.PostRecipeService;
import jp.co.axiz.web.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeRestController {
    @Autowired
    HttpSession session;
    @Autowired
    private GoodService goodService;
    @Autowired
    private PostRecipeService postRecipeService;
    @Autowired
    private MadeRecipeService madeRecipeService;
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getInfo")
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", (UserInfo) session.getAttribute("user"));
        map.put("recipeId", (Integer) session.getAttribute("recipeId"));
        return map;
    }

    @GetMapping("/goodCheck")
    public Good todaysChecked(@RequestParam(name = "recipeId", required = false) Integer recipeId,
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day) {
        System.out.println(recipeId + ", " + userId + ", " + year + ", " + month + ", " + day);
        Good good = new Good(recipeId, userId, year, month, day);
        return goodService.todaysChecked(good);
    }

    @GetMapping("/madeCheck")
    public MadeRecipe todaysMadeChecked(@RequestParam(name = "recipeId", required = false) Integer recipeId,
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day) {
        System.out.println(recipeId + ", " + userId + ", " + year + ", " + month + ", " + day);
        MadeRecipe madeRecipe = new MadeRecipe(userId, year, month, day, recipeId);
        return madeRecipeService.checkMadeRecipe(madeRecipe);
    }

    @PostMapping("/good")
    public Integer goodCheck(@RequestParam(name = "recipeId", required = false) Integer recipeId,
            @RequestParam(name = "userId", required = false) Integer userId) {
        Good good = new Good(recipeId, userId);
        goodService.insertGood(good);
        System.out.println(recipeId);
        System.out.println(recipeService.totalGood(recipeId));
        return recipeService.totalGood(recipeId);
    }

    @DeleteMapping("/good/{recipeId}/{userId}/{year}/{month}/{day}")
    public Integer goodReset(@PathVariable(name = "recipeId") Integer recipeId,
            @PathVariable(name = "userId") Integer userId, @PathVariable(name = "year") Integer year,
            @PathVariable(name = "month") Integer month, @PathVariable(name = "day") Integer day) {
        Good good = new Good(recipeId, userId, year, month, day);
        goodService.deleteGood(good);
        System.out.println(recipeService.totalGood(recipeId));
        return recipeService.totalGood(recipeId);
    }

    @PostMapping("/made")
    public void madeCheck(@RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "recipeId", required = false) Integer recipeId) {
        madeRecipeService.insertMadeRecipe(userId, recipeId);
    }

    @DeleteMapping("/made/{recipeId}/{userId}/{year}/{month}/{day}")
    public void madeReset(@PathVariable(name = "recipeId") Integer recipeId,
            @PathVariable(name = "userId") Integer userId, @PathVariable(name = "year") Integer year,
            @PathVariable(name = "month") Integer month, @PathVariable(name = "day") Integer day) {
        madeRecipeService.deleteMadeRecipe(userId, recipeId, year, month, day);

    }

    @DeleteMapping("/post/{recipeId}")
    public void deleteRecipe(@PathVariable(name = "recipeId") Integer recipeId) {
        postRecipeService.deletePostRecipe(recipeId);
        recipeService.deleteRecipe(recipeId);
    }
}
