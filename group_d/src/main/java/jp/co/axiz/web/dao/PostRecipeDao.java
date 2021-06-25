package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.PostRecipe;

public interface PostRecipeDao {
    public List<PostRecipe> postRecipeList(PostRecipe postRecipe);

    public List<PostRecipe> allPostRecipeList(Integer userId);
}