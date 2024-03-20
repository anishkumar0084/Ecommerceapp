package com.ecommericeapp.Data;

public class orderDetail {
    String date;
    String title;
    String size;
    String order_id;
    String price;
    String discount;
    String total_amount;
    String payment_method;
    String currentdate;



    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getSht_d() {
        return sht_d;
    }

    public void setSht_d(String sht_d) {
        this.sht_d = sht_d;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    String quantity,charge,offer,sht_d,sizes;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public orderDetail(String date, String title, String size, String order_id, String price, String discount, String total_amount, String payment_method, String image, String cureentdate, String quantity, String charge, String offer, String sht_d, String sizes, String id) {
        this.date = date;
        this.title = title;
        this.size = size;
        this.quantity=quantity;
        this.charge=charge;
        this.offer=offer;
        this.id=id;
        this.sht_d=sht_d;
        this.sizes=sizes;
        this.order_id = order_id;
        this.price = price;
        this.discount = discount;
        this.total_amount = total_amount;
        this.payment_method = payment_method;
        this.image=image;
        this.currentdate=cureentdate;

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
