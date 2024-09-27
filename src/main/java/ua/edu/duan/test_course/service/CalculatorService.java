package ua.edu.duan.test_course.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("Divide on 0 is impossible");
        }
        return (double) a / b;
    }
}
