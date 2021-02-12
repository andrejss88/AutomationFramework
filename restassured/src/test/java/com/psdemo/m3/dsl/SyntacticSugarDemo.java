package com.psdemo.m3.dsl;

public class SyntacticSugarDemo {


    public static void main(String[] args) {
        
        new Account(100)
                .withdraw(20)
                .printBalance();

        new Account(100)
                .withdraw(30)
                .andThen()
                .printBalance();
    }
}
