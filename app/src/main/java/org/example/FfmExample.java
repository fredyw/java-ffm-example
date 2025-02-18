package org.example;

public class FfmExample {
    public record Input(int a, int b, char op) {}

    public static int calculate(Input input) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(calculate(new Input(1, 2, '+')));
        System.out.println(calculate(new Input(4, 2, '-')));
        System.out.println(calculate(new Input(1, 2, '*')));
        System.out.println(calculate(new Input(4, 2, '/')));
    }
}
