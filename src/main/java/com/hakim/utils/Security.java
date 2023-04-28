package com.hakim.utils;

import com.hakim.pojo.Person;

import java.util.Scanner;

public class Security {
    private static final Scanner input = new Scanner(System.in);
    public static void changePassword(Person person) {
        System.out.println("Change Password Option - ");
        System.out.println("Enter old PIN : ");

        String oldPassword = input.next();
        if (!person.getPin().equals(oldPassword)) {
            System.out.println("Invalid Credentials!");
            return;
        }
        System.out.println("Enter new PIN : ");
        String newPassword = input.next();
        person.setPin(newPassword);
    }

}
