package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверяет методы класса CustomValues3
 */
class CustomValues3Test {
    private CustomValues3 customValues3;
    @BeforeEach
    void setUp() {
        customValues3 = new CustomValues3(1, 2, 3L);
    }
    /**
     * Проверяет правильно ли возвращается первое значение
     */
    @Test
    void getValue1Test() {
        assertEquals(1, customValues3.getValue1());
    }
    /**
     * Проверяет правильно ли возвращается второе значение
     */
    @Test
    void getValue2Test() {
        assertEquals(2, customValues3.getValue2());
    }

    /**
     * Проверяет правильно ли возвращается третье значение
     */
    @Test
    void getValue3Test() {
        assertEquals(3L, customValues3.getValue3());
    }

    /**
     * Проверяет изменение первого значения
     */
    @Test
    void setValue1Test() {
        customValues3.setValue1(4);
        assertEquals(4, customValues3.getValue1());
    }

    /**
     * Проверяет изменения второго значения
     */
    @Test
    void setValue2Test() {
        customValues3.setValue2(5);
        assertEquals(5, customValues3.getValue2());
    }

    /**
     * Проверяет изменения третьего значения
     */
    @Test
    void setValue3Test() {
        customValues3.setValue3(6L);
        assertEquals(6L, customValues3.getValue3());
    }
}