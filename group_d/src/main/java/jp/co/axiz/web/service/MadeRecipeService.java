package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.MadeRecipe;

public interface MadeRecipeService {
    public List<MadeRecipe> getMadeRecipe(MadeRecipe madeRecipe);

    public List<MadeRecipe> getAllMadeRecipe(Integer userId);
}
