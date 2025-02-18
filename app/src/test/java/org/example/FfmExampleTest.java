package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FfmExampleTest {
    @Test
    void add() throws Throwable {
        assertEquals(3, FfmExample.calculate(new FfmExample.Input(1, 2, '+')));
    }

    @Test
    void substract() throws Throwable {
        assertEquals(2, FfmExample.calculate(new FfmExample.Input(4, 2, '-')));
    }

    @Test
    void multiply() throws Throwable {
        assertEquals(2, FfmExample.calculate(new FfmExample.Input(1, 2, '*')));
    }

    @Test
    void divide() throws Throwable {
        assertEquals(2, FfmExample.calculate(new FfmExample.Input(4, 2, '/')));
    }
}
