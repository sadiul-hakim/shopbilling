package com.hakim.pojo;

public class Person {
    private String name;
    private Address address;
    private Contact contact;
    private String pin;
    private String role;

    public Person(String name, Address address, Contact contact, String pin, String role) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.pin = pin;
        this.role = role;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", role='" + role + '\'' +
                '}';
    }
}
