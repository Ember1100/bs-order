package cn.baisexy.bs_order.system.service;

import cn.baisexy.bs_order.api.entity.Food;

import java.util.List;

public interface FoodService {

    List<Food> findAll();

    List<Food> findAll2();

    List<Food> getFoodsByType(Integer cId);

    int updateNum(Integer odFId,Integer num);

    Food findById(Integer fid);

    List<Food> getFood(int currentPage, int limit);

    int save(Food food);

    int del(Integer fid);
}
