package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.MadeRecipe;

public interface MadeRecipeDao {
    public List<MadeRecipe> madeRecipeList(MadeRecipe MadeRecipe);

    public MadeRecipe checkMadeRecipe(MadeRecipe MadeRecipe);

    public List<MadeRecipe> allMadeRecipeList(Integer userId);

    public void insertMadeRecipe(Integer userId, Integer recipeId);

    public void deleteMadeRecipe(Integer userId, Integer recipeId, Integer year, Integer month, Integer day);

    public void deleteMadeByRecipeId(Integer recipeId);

    public void deleteMadeByUserId(Integer userId);

}
