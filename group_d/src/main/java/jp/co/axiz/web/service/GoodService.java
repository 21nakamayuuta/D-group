package jp.co.axiz.web.service;

import jp.co.axiz.web.entity.Good;

public interface GoodService {
    public Good todaysChecked(Good good);

    public void insertGood(Good good);

    public void deleteGood(Good good);
}
