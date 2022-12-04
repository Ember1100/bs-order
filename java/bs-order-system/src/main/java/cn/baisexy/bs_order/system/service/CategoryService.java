package cn.baisexy.bs_order.system.service;

import cn.baisexy.bs_order.api.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    int update(Category category);

    int add(Category category);

    int del(Integer cId);

    List<Category> findAll2();
}
