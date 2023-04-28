package com.hakim.pojo;

public class Address {
    private String city;
    private String district;
    private String sub_district;
    private int zip;

    public Address(String city, String district, String sub_district, int zip) {
        this.city = city;
        this.district = district;
        this.sub_district = sub_district;
        this.zip = zip;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", sub_district='" + sub_district + '\'' +
                ", zip=" + zip +
                '}';
    }
}
