package cn.baisexy.bs_order.controller;

import cn.baisexy.bs_order.api.dto.OrdersDTO;
import cn.baisexy.bs_order.api.entity.OrderDetail;
import cn.baisexy.bs_order.api.entity.Orders;
import cn.baisexy.bs_order.api.entity.User;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.api.pojo.send.TableReturn;
import cn.baisexy.bs_order.api.vo.Order;
import cn.baisexy.bs_order.system.service.FoodService;
import cn.baisexy.bs_order.system.service.OrderService;
import cn.baisexy.bs_order.system.service.UserService;
import cn.baisexy.bs_order.system.service.impl.DataAnalyzeService;
import cn.baisexy.bs_order.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private DataAnalyzeService dataAnalyzeService;


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,2,TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

    @GetMapping("/getOrdersByUser")
    public ResultUtil getOrders(Integer uId) {
        System.out.println(uId);
        List<Orders> orders = orderService.getOrders(uId);
        System.out.println(orders);
        if (orders != null) {
            return new ResultUtil(orders, ReturnCode.SUCCESS);
        }
        return new ResultUtil(orders, ReturnCode.FAIL);
    }

    @RequestMapping("/addOrder")
    @Transactional
    public ResultUtil addOrder(@RequestBody OrdersDTO ordersDTO) throws ExecutionException, InterruptedException, TimeoutException {
        if (ordersDTO == null) return new ResultUtil(ReturnCode.FAIL);
        System.out.println(ordersDTO);
        //异步请求
        Future<Integer> integerFuture = executor.submit(() -> orderService.getMax()+1);

        new Thread(() -> {
            for (Order order : ordersDTO.getOrderDetailList()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setODFId(order.getOdFId());
                try {
                    orderDetail.setODOId(integerFuture.get(1,TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    throw new RuntimeException(e);
                }
                orderDetail.setODFAmount(order.getOdFAmount());
                dataAnalyzeService.addOrderDetail(orderDetail);
                foodService.updateNum(order.getOdFId(), order.getOdFAmount());
            }
        }).start();

//        User user = userService.getUserById(ordersDTO.getOUId());
        //异步请求获取user
//        1.使用Future
            Future<User> future =  executor.submit(() -> userService.getUserById(ordersDTO.getOUId()));
            Boolean b = orderService.addOrder(ordersDTO, future.get(1, TimeUnit.SECONDS));

             if (!b) {
                return new ResultUtil(ReturnCode.FAIL);
             }

//        //2.使用CompletableFuture
//        CompletableFuture completableFuture =   CompletableFuture.supplyAsync(() -> userService.getUserById(ordersDTO.getOUId()));
//
//        completableFuture.thenAccept((Consumer<User>) user -> orderService.addOrder(ordersDTO,user));

//

        return new ResultUtil(ReturnCode.SUCCESS);

    }


    @GetMapping("/changeStatus")
    public ResultUtil changeStatus(Integer oid) {
        System.out.println(oid);
        Orders byId = orderService.findOrder(oid);
        if (byId == null || byId.getoStatus().equals("用户已接收")) {
            return new ResultUtil(ReturnCode.FAIL);
        }
        orderService.changeStatus(byId.getoStatus(), byId.getoId());
        return new ResultUtil(ReturnCode.SUCCESS);
    }


    @PostMapping("/getOrders")
    public TableReturn getOrders(@RequestBody TableReturn tableReturn) {
        List<Orders> all = orderService.findOrders(tableReturn.getTotal(), tableReturn.getPageNum());
        TableReturn tr = new TableReturn(all, ReturnCode.SUCCESS);
        tr.setTotal(orderService.getMax());
        return tr;
    }


    @GetMapping("/delOrder")
    public ResultUtil delOrder(Integer oid) {
        if (oid == null) return new ResultUtil(ReturnCode.FAIL);
        int i = orderService.delOrder(oid);
        if (i == 1) return new ResultUtil<>(ReturnCode.SUCCESS);
        return new ResultUtil<>(ReturnCode.FAIL);
    }

}
