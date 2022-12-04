package cn.baisexy.bs_order.api.dto;

import cn.baisexy.bs_order.api.entity.Orders;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserOrderVO extends Orders {
   private String uName;
}
