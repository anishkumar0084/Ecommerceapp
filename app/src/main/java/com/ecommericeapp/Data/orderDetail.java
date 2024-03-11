package com.ecommericeapp.Data;

public class orderDetail {
    String date,title,size,order_id,price,discount,total_amount,payment_method;

    public orderDetail(String date, String title, String size, String order_id, String price, String discount, String total_amount, String payment_method) {
        this.date = date;
        this.title = title;
        this.size = size;
        this.order_id = order_id;
        this.price = price;
        this.discount = discount;
        this.total_amount = total_amount;
        this.payment_method = payment_method;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }


}
