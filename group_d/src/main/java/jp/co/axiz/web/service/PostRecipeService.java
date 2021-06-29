package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.PostRecipe;

public interface PostRecipeService {
    public List<PostRecipe> getPostRecipe(PostRecipe postRecipe);

    public List<PostRecipe> getAllPostRecipe(Integer userId);

    public void insertPostRecipe(Integer userId, Integer recipeId);

    public void deletePostRecipe(Integer recipeId);

    public void deletePostByUserId(Integer userId);
}
