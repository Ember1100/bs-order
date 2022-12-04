package cn.baisexy.bs_order.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {
    Integer oDId;
    Integer oDOId;
    Integer oDFAmount;
    Integer oDFId;

}
