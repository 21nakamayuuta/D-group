package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.MadeRecipeDao;
import jp.co.axiz.web.entity.MadeRecipe;
import jp.co.axiz.web.service.MadeRecipeService;

@Service
public class MadeRecipeServiceImpl implements MadeRecipeService {
    @Autowired
    MadeRecipeDao madeRecipeDao;

    @Override
    public List<MadeRecipe> getMadeRecipe(MadeRecipe MadeRecipe) {
        return madeRecipeDao.madeRecipeList(MadeRecipe);
    }

    @Override
    public List<MadeRecipe> getAllMadeRecipe(Integer userId) {
        return madeRecipeDao.allMadeRecipeList(userId);
    }

    @Override
    public void insertMadeRecipe(Integer userId, Integer recipeId) {
        madeRecipeDao.insertMadeRecipe(userId, recipeId);
    }

    @Override
    public void deleteMadeRecipe(Integer userId, Integer recipeId, Integer year, Integer month, Integer day) {
        madeRecipeDao.deleteMadeRecipe(userId, recipeId, year, month, day);
    }
}
