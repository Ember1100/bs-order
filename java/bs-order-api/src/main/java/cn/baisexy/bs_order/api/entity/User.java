package cn.baisexy.bs_order.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer uId;

    private String uName;

    private String uPwd;

    private String uPn;

    private String uAddress;

    private static final long serialVersionUID = 1L;

    private String code;

}