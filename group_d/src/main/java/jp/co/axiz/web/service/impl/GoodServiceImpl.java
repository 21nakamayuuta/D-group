package jp.co.axiz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.GoodDao;
import jp.co.axiz.web.entity.Good;
import jp.co.axiz.web.entity.Recipe;
import jp.co.axiz.web.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Override
    public void insertGood(Good good) {
        goodDao.insertGood(good);
    }

    @Override
    public void deleteGood(Good good) {
        goodDao.deleteGood(good);
    }

    @Override
    public void deleteGoodByRecipeId(Integer recipeId) {
        goodDao.deleteGoodByRecipeId(recipeId);
    }

    @Override
    public void deleteGoodByUserId(Integer userId) {
        goodDao.deleteGoodByUserId(userId);
    }

    @Override
    public Good todaysChecked(Good good) {
        return goodDao.todaysChecked(good);
    }
}
