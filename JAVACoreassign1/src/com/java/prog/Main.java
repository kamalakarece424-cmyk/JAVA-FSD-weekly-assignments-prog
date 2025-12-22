package com.java.prog;
class employee {

    String name;
    double salary;

    employee(String name, double salary) {
        this.name = name;
        this.salary =salary;
    }

    double calculateSalary() {
        return salary;
    }
}

class Manager extends employee{

    double bonus;

    Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return salary + bonus;
    }
}

public class Main {

    public static void main(String[] args) {

        employee emp = new employee("kamal", 40000);
        employee mgr = new Manager("ram", 50000, 20000);
        System.out.println("Employee Salary: " + emp.calculateSalary());
        System.out.println("Manager Salary: " + mgr.calculateSalary());
    }
}