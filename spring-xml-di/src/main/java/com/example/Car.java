package com.example;

public class Car {
    private Engine1 engine1;

    // Setter for DI
    public void setEngine(Engine1 engine1) {
        this.engine1 = engine1;
    }

    public void run() {
         engine1.start();
        System.out.println("Car is running...");
    }
}
