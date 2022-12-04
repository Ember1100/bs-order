package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.entity.Category;
import cn.baisexy.bs_order.api.entity.Food;
import cn.baisexy.bs_order.system.mapper.CategoryMapper;
import cn.baisexy.bs_order.system.mapper.FoodMapper;
import cn.baisexy.bs_order.system.service.CategoryService;
import cn.baisexy.bs_order.system.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {



    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private FoodService foodService;

    @Override
    public int del(Integer cId) {
        return categoryMapper.deleteByPrimaryKey(cId);
    }

    @Override
    public List<Category> findAll2() {
        return categoryMapper.getAll();
    }

    @Override
    public int add(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.updateByPrimaryKey(category);
    }
    @Override

    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        for (Category category : categoryMapper.getAll()) {
             int i = category.getCId();
            List<Food> foodsType = foodService.getFoodsByType(i);
            category.setFoods(foodsType);
            list.add(category);
        }
        return list;
    }



}
