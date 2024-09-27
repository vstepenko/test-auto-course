package ua.edu.duan.test_course.service;


public class AddMethodTestModel {

    final int  a;
    final int b;
    final int expected;

    public AddMethodTestModel(int a, int b, int expected) {
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getExpected() {
        return expected;
    }

}
