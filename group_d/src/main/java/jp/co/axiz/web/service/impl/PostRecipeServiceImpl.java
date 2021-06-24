package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.PostRecipeDao;
import jp.co.axiz.web.entity.PostRecipe;
import jp.co.axiz.web.service.PostRecipeService;

@Service
public class PostRecipeServiceImpl implements PostRecipeService {
    @Autowired
    PostRecipeDao postRecipeDao;

    @Override
    public List<PostRecipe> getPostRecipe(PostRecipe postRecipe) {
        return postRecipeDao.postRecipeList(postRecipe);
    }

    @Override
    public List<PostRecipe> getAllPostRecipe(Integer userId) {
        return postRecipeDao.allPostRecipeList(userId);
    }
}
