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

    String price;
    String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public cartdata(String price, String title, String image) {
        this.price = price;
        this.title = title;
        this.image=image;
    }
}
