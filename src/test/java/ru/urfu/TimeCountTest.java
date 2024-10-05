package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирующий работоспосбность класса TimeCount
 */
class TimeCountTest {
    private TimeCount timeCounter;
    @BeforeEach
    void setUp() {
        timeCounter = new TimeCount();
    }

    /**
     * Проверяет правильно ли добавляются пользователи в класс
     * правильно ли подсчитывается количество пользователей
     * правильно ли удаляются пользователи
     */
    @Test
    void testStartTimeUserAndTestSizeTimeCountAndTestRemoveTimeUser() {
        String user1 = "user1";
        assertEquals(0,timeCounter.sizeTimeCount());
        timeCounter.startTimeUser(user1);
        assertEquals(1,timeCounter.sizeTimeCount());
        timeCounter.removeTimeUser(user1);
        assertEquals(0,timeCounter.sizeTimeCount());
    }

    /**
     * Проверяет правильно ли рассчитывает время выполнения пользователем теста
     */
    @Test
    void endTimeUser() throws InterruptedException{
        String user2 = "user2";
        timeCounter.startTimeUser(user2);
        Thread.sleep(100);
        Long elapsedTime = timeCounter.endTimeUser(user2);
        assertEquals(100,elapsedTime,50);
        timeCounter.removeTimeUser(user2);
    }

}