package cn.baisexy.bs_order.system.service;

import cn.baisexy.bs_order.api.dto.OrdersDTO;
import cn.baisexy.bs_order.api.entity.Orders;
import cn.baisexy.bs_order.api.entity.User;

import java.util.List;

public interface OrderService {

    boolean addOrder(OrdersDTO ordersDTO, User user);

    List<Orders> getOrders(Integer oUId);

    Orders findOrder(Integer oId);

    void changeStatus(String status, Integer oId);


    List<Orders> findOrders(int total, int pageNum);

    Integer getMax();

    int delOrder(Integer oid);
}
