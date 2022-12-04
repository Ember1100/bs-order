package cn.baisexy.bs_order.api.dto;

import cn.baisexy.bs_order.api.vo.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersDTO {
    private String oPayment;
    private Integer oUId;
    private BigDecimal oPrice;
    private List<Order> orderDetailList;
}
