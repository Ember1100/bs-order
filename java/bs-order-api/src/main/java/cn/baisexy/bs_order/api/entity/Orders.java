package cn.baisexy.bs_order.api.entity;


import java.io.Serializable;
import java.math.BigDecimal;



public class Orders implements Serializable {
    private Integer oId;

    private String oDate;

    private String oPayment;

    private Integer oUId;

    private BigDecimal oPrice;

    private String oStatus;

    private String oContent;

    private String oAddress;



    private static final long serialVersionUID = 1L;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate == null ? null : oDate.trim();
    }

    public String getoPayment() {
        return oPayment;
    }

    public void setoPayment(String oPayment) {
        this.oPayment = oPayment == null ? null : oPayment.trim();
    }

    public Integer getoUId() {
        return oUId;
    }

    public void setoUId(Integer oUId) {
        this.oUId = oUId;
    }

    public BigDecimal getoPrice() {
        return oPrice;
    }

    public void setoPrice(BigDecimal oPrice) {
        this.oPrice = oPrice;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus == null ? null : oStatus.trim();
    }

    public String getoContent() {
        return oContent;
    }

    public void setoContent(String oContent) {
        this.oContent = oContent == null ? null : oContent.trim();
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress == null ? null : oAddress.trim();
    }
}