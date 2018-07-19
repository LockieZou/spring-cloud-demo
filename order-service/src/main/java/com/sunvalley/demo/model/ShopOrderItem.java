package com.sunvalley.demo.model;


import java.math.BigDecimal;
import java.util.Date;

public class ShopOrderItem extends BaseModel {
    private Integer orderId;

    private Integer productId;

    private Integer qtyOrdered;

    private BigDecimal price;

    private BigDecimal totalPrice;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(Integer qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}