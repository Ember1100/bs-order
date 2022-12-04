package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.dto.OrdersDTO;
import cn.baisexy.bs_order.api.vo.Order;
import cn.baisexy.bs_order.api.entity.Orders;
import cn.baisexy.bs_order.api.entity.User;
import cn.baisexy.bs_order.api.pojo.toString.ListToString;
import cn.baisexy.bs_order.system.mapper.FoodMapper;
import cn.baisexy.bs_order.system.mapper.OrdersMapper;
import cn.baisexy.bs_order.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FoodMapper foodMapper;


    @Override
    public boolean addOrder(OrdersDTO ordersDTO, User user) {
        Orders orders = new Orders();
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String oDate = sdf.format(date);
//        System.out.println(oDate);
        orders.setoDate(oDate);
        orders.setoStatus("饭店接单中");
        orders.setoAddress(user.getUAddress());
        Integer maxId = getMax();
        if ((maxId==null)) {
            maxId = 0;
        }
        orders.setoId(maxId+1);
        orders.setoUId(ordersDTO.getOUId());
        orders.setoPayment(ordersDTO.getOPayment());
        orders.setoPrice(ordersDTO.getOPrice());
        List<Order> orderList = ordersDTO.getOrderDetailList();
        String oContent =  new ListToString().toString(orderList);
        orders.setoContent(oContent);
        return  ordersMapper.insertSelective(orders)==1;
    }

    @Override
    public List<Orders> getOrders(Integer uId) {
        return ordersMapper.findOrdersByUser(uId);
    }

    @Override
    public Orders findOrder(Integer oId) {
        return ordersMapper.selectByPrimaryKey(oId);
    }

    public Integer getMax() {
        return ordersMapper.getMaxId();
    }

    public void changeStatus(String status, Integer oId) {
        if (status.equals("饭店接单中")) {
            status = "外卖小哥送餐中";
        } else {
            status = "用户已接收";
        }
        int i = ordersMapper.updateStatus(status, oId);
    }

    @Override
    public List<Orders> findOrders(int total, int pageNum) {
        List<Orders> all = ordersMapper.findOrder(total * (pageNum - 1), total);
        return all;
    }

    @Override
    public int delOrder(Integer oid) {
        return ordersMapper.deleteByPrimaryKey(oid);
    }



}
