package jp.co.axiz.web.dao;

import jp.co.axiz.web.entity.Good;

public interface GoodDao {
    public Good todaysChecked(Good good);

    public void insertGood(Good good);

    public void deleteGood(Good good);
}