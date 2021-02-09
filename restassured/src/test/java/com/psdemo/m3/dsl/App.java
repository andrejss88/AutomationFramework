package com.psdemo.m3.dsl;

public class App {

    public static void main(String[] args) {

        double balance = new Person()
                .id(1)
                .name("John")
                .withAccount(new Account(10))
                .getBalance();

        System.out.println(balance);
    }
}
