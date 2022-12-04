package cn.baisexy.bs_order.api.pojo.toString;

import cn.baisexy.bs_order.api.vo.Order;

import java.util.List;

public class ListToString {

    public String toString(List<Order> orderList) {
        String oContent = "";
        String start = "#";
        String middle = "-";
        for (Order o: orderList) {
            Integer odFAmount = o.getOdFAmount();
            String fName = o.getFName();
            oContent=oContent+start+fName+middle+odFAmount;
        }

        return oContent;
    }


//    public static void main(String[] args) {
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setFName("可乐");
//        orderDetail.setOdFAmount(3);
//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setFName("烤肉");
//        orderDetail2.setOdFAmount(4);
//        orderDetailList.add(orderDetail);
//        orderDetailList.add(orderDetail2);
//        String s = new ListToString().toString(orderDetailList);
//        System.out.println(s);
//    }
}
