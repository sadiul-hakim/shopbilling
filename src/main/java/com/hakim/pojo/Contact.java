package com.hakim.pojo;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private List<String> phone=new ArrayList<>();
    private String email;

    public Contact(List<String> phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public Contact() {
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
