package com.github.statuscodes;

public class TestDrive {


    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        String operator = args[1];
        int b = Integer.parseInt(args[2]);

        if (operator.equals("+")) {
            print(a + b);
        } else if (operator.equals("-")) {
            print(a - b);
        } else if (operator.equals("*")) {
            print(a + b);
        } else if (operator.equals("/")) {
            print(a / b);
        }
    }

    private static void print(int i) {
        System.out.println(i);
    }
}
