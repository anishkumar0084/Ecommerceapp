package com.ecommericeapp.Data;

public class cartdata {



    String price,title,image,sizek,quantitys,charge,offer,sht_d,sizes,discount;

    public cartdata(String price, String title, String image, String sizek, String quantitys, String charge, String offer, String sht_d, String sizes, String discount) {
        this.price = price;
        this.title = title;
        this.image = image;
        this.sizek = sizek;
        this.quantitys = quantitys;
        this.charge = charge;
        this.offer = offer;
        this.sht_d = sht_d;
        this.sizes = sizes;
        this.discount = discount;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSizek() {
        return sizek;
    }

    public void setSizek(String sizek) {
        this.sizek = sizek;
    }

    public String getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(String quantitys) {
        this.quantitys = quantitys;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
