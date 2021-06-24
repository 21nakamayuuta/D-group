package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.MadeRecipe;

public interface MadeRecipeDao {
    public List<MadeRecipe> madeRecipeList(MadeRecipe MadeRecipe);

    public List<MadeRecipe> allMadeRecipeList(Integer userId);

}
