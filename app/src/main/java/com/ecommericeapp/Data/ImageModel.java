package com.ecommericeapp.Data;

// ImageModel.java
public class ImageModel {
    private String url;

    public ImageModel() {
        // Default constructor required for calls to DataSnapshot.getValue(ImageModel.class)
    }

    public ImageModel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

