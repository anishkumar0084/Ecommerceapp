package com.ecommericeapp.Data;

public class cartdata {

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String price,title;

    public cartdata(String price, String title) {
        this.price = price;
        this.title = title;
    }
}
