package com.ecommericeapp.Data;

public class productDetail {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String id,title,price,image;
    public productDetail(String id, String title, String price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }





}
