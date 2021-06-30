package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.MadeRecipe;

public interface MadeRecipeService {
    public List<MadeRecipe> getMadeRecipe(MadeRecipe madeRecipe);

    public MadeRecipe checkMadeRecipe(MadeRecipe MadeRecipe);

    public List<MadeRecipe> getAllMadeRecipe(Integer userId);

    public void insertMadeRecipe(Integer userId, Integer recipeId);

    public void deleteMadeRecipe(Integer userId, Integer recipeId, Integer year, Integer month, Integer day);

    public void deleteMadeRecipeId(Integer recipeId);

    public void deleteMadeUserId(Integer userId);
}
