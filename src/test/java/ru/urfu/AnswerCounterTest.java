package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирующи работу класса AnswerCounter
 */
class AnswerCounterTest {

    private AnswerCounter counter;

    @BeforeEach
    void setUp() {
        counter = new AnswerCounter();
    }

    /**
     * Проверяет методы добавления пользователя в HashMap,
     * удаления пользователей из HashMap,
     * количество пользователей в Hashmap
     */
    @Test
    void testAddUserAndTestRemoveUserAndTestGetUserCount(){
        String user1 = "user1";
        assertEquals(0,counter.getUserCount());
        counter.addUser(user1);
        assertEquals(1,counter.getUserCount());
        counter.removeUser(user1);
        assertEquals(0,counter.getUserCount());
        counter.removeUser(user1);
    }

    /**
     * Проверяет добавление еденицы в случае правильного ответа пользователя
     */
    @Test
    void testAddCountAnswer() {
        String user2 = "user2";
        counter.addUser(user2);
        counter.addCountAnswer(user2, true);
        assertTrue(counter.getCountAnswer(user2) == 1);
        counter.addCountAnswer(user2, false);
        assertTrue(counter.getCountAnswer(user2) == 1);
        counter.getCountAnswer(user2);
        counter.removeUser(user2);
    }

    /**
     * Проверяет метод возврата количества правильных ответов на вопросы пользователя
     */
    @Test
    void testGetCountAnswer() {
        String user3 = "user3";
        counter.addUser(user3);
        counter.addCountAnswer(user3, true);
        counter.addCountAnswer(user3, true);
        assertTrue(counter.getCountAnswer(user3) == 2);
        counter.removeUser(user3);
    }
}