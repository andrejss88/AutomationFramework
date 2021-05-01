package com.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class Quicktest {

    static long totalDuration;

    @BeforeMethod
    void setup() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(500);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        totalDuration = totalDuration + timeElapsed;
        System.out.println("Elapsed time " + timeElapsed);
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("test");
    }

    @Test
    public void test2(){
        System.out.println("test 2");
    }

    @AfterSuite
    void print() {
        System.out.println(totalDuration);
    }
}
