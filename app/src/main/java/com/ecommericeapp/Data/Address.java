package com.ecommericeapp.Data;

public class Address {
    String name,phone,Pin_code,state,city,House_no,Road_name,Address_type;

    public Address(String name, String phone, String pin_code, String state, String city, String house_no, String road_name, String address_type) {
        this.name = name;
        this.phone = phone;
        Pin_code = pin_code;
        this.state = state;
        this.city = city;
        House_no = house_no;
        Road_name = road_name;
        Address_type = address_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPin_code() {
        return Pin_code;
    }

    public void setPin_code(String pin_code) {
        Pin_code = pin_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse_no() {
        return House_no;
    }

    public void setHouse_no(String house_no) {
        House_no = house_no;
    }

    public String getRoad_name() {
        return Road_name;
    }

    public void setRoad_name(String road_name) {
        Road_name = road_name;
    }

    public String getAddress_type() {
        return Address_type;
    }

    public void setAddress_type(String address_type) {
        Address_type = address_type;
    }

}
