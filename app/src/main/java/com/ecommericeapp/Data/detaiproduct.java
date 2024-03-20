package com.ecommericeapp.Data;

public class detaiproduct {
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
    String rating,comment;


    String size;
    String totalratingk,average;


    public detaiproduct(String id, String title, String price, String srt_image, String image1, String image2, String image3, String image4, String discount, String delivery_charge, String offer, String srt_desc,String size,String rating,String comment,String totalratignk,String aveage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.srt_image = srt_image;
        this.image1 = image1;
        this.rating=rating;
        this.comment=comment;
        this.size=size;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.discount = discount;
       this.Delivery_charge = delivery_charge;
        this.offer = offer;
        this.srt_desc = srt_desc;
        this.totalratingk=totalratignk;
        this.average=aveage;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



    public String getTotalratingk() {
        return totalratingk;
    }

    public void setTotalratingk(String totalratingk) {
        this.totalratingk = totalratingk;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
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



