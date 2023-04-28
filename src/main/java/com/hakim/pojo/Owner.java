package com.hakim.pojo;

public class Owner extends Person{
    public Owner(String name, Address address, Contact contact, String pin, String role) {
        super(name, address, contact, pin, role);
    }

    public Owner() {
    }
}
