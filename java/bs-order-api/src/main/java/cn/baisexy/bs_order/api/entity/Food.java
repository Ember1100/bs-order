package cn.baisexy.bs_order.api.entity;



import lombok.Data;

import java.io.Serializable;

@Data
public class Food implements Serializable {
    private Integer fId;

    private String fName;

    private Integer fPrice;

    private String fImg;

    private Integer fCId;

    private String fDes;

    private Integer fAmount;

    private static final long serialVersionUID = 1L;


}