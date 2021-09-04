package com.example.blooddonation;

public class Hospital {
    private String name;
    private String address;
    private String pincode;
    private String image;
    private String phone;
    private String fax;


    public Hospital() {
    }

    public Hospital(String name, String address, String pincode,String image, String phone, String fax) {
        this.name = name;
        this.address = address;
        this.pincode= pincode;
        this.image = image;
        this.phone = phone;
        this.fax = fax;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }



    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
