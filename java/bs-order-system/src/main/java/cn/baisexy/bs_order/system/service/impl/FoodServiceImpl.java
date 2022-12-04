package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.entity.Food;
import cn.baisexy.bs_order.system.mapper.FoodMapper;
import cn.baisexy.bs_order.system.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<Food> findAll() {
        return foodMapper.getAll();
    }

    @Override
    public List<Food> findAll2() {
        return foodMapper.getAll2();
    }

    @Override
    public List<Food> getFoodsByType(Integer cId) {
        return foodMapper.getFoodsByType(cId);
    }


    @Override
    public int updateNum(Integer odFId, Integer num) {
        return foodMapper.updateInventory(odFId,num);
    }

    @Override
    public int del(Integer fid) {
        return foodMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public Food findById(Integer fid) {
        return foodMapper.findById(fid);
    }

    @Override
    public List<Food> getFood(int currentPage, int limit) {
        int begin = (currentPage - 1) * limit;
        return foodMapper.getLimitFood(begin, limit);
    }

    @Override
    public int save(Food food) {
        if (food.getFId()==null) {
           return foodMapper.insert(food);
        }
        return foodMapper.updateByPrimaryKey(food);
    }

}
