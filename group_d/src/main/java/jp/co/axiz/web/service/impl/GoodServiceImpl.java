package jp.co.axiz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.GoodDao;
import jp.co.axiz.web.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Override
    public void insertGood(Integer recipeId, Integer userId) {
        goodDao.insertGood(recipeId, userId);
    }

    @Override
    public void deleteGood(Integer goodId) {
        goodDao.deleteGood(goodId);
    }
}
