package cn.baisexy.bs_order.api.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Order implements Serializable {

   private Integer odFAmount;
   private Integer odFId;
   private String fName;
}
