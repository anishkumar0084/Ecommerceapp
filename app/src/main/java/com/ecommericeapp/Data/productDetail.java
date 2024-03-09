package com.ecommericeapp.Data;

public class productDetail {
    String id;
    String title;
    String price;
    String srt_image;
    String image1;
    String image2;
    String image3;
    String image4;
    String discount;
    String Delivery_charge;
    String offer;
    String srt_desc;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    String size;

    public productDetail(String id, String title, String price, String srt_image, String image1, String image2, String image3, String image4, String discount, String delivery_charge, String offer, String srt_desc,String size) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.srt_image = srt_image;
        this.image1 = image1;
        this.size=size;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.discount = discount;
        Delivery_charge = delivery_charge;
        this.offer = offer;
        this.srt_desc = srt_desc;
    }

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

    public String getSrt_image() {
        return srt_image;
    }

    public void setSrt_image(String srt_image) {
        this.srt_image = srt_image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDelivery_charge() {
        return Delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        Delivery_charge = delivery_charge;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getSrt_desc() {
        return srt_desc;
    }

    public void setSrt_desc(String srt_desc) {
        this.srt_desc = srt_desc;
    }
}
