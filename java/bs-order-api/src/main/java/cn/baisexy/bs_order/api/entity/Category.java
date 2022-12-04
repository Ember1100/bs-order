package cn.baisexy.bs_order.api.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Category implements Serializable {
    private Integer cId;

    private String cName;

    private String cDes;

    private static final long serialVersionUID = 1L;

    private List<Food> foods;

}