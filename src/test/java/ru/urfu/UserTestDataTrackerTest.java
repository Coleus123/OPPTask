package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирующий методы класса UserTestDataTracker
 */
class UserTestDataTrackerTest {
    private UserTestDataTracker testDataTracker;
    @BeforeEach
    void setUp() {
        testDataTracker = new UserTestDataTracker();

    }

    /**
     * Проверяет метод на добавление AppData,
     * возвращения номера ответа, на котором сейчас пользователь GetNumberOfQuestion,
     * возвращения количества правильных отетов GetRightNumberOfQuestion,
     * возвращения времени начала теста GetUserTime
     */
    @Test
    void addDataTestAndGetNumberOfQuestionTestAndGetRightNumberOfQuestionTestAndGetUserTimeTest()
            throws InterruptedException {
        testDataTracker.addData("user1","Математика",2l);
        assertEquals("Математика", testDataTracker.getSubject("user1"));
        assertEquals(2l, testDataTracker.getOption("user1"));
        assertEquals(1, testDataTracker.getNumberOfQuestion("user1"));
        assertEquals(2, testDataTracker.getRightNumberOfQuestion("user1"));
        Thread.sleep(100);
        assertEquals(System.currentTimeMillis() - testDataTracker.getUserTime("user1")
                , 100, 50);
    }


    /**
     * Проверяет метод AddNumberOfQuestion на добавление еденицы к номеру вопроса, на котором сейчас пользователь
     */
    @Test
    void addNumberOfQuestion() {
        testDataTracker.addData("user2","Информатика",2L);
        testDataTracker.addNumberOfQuestion("user2");
        assertEquals(2, testDataTracker.getNumberOfQuestion("user2"));
    }

    /**
     * Проверяет на добавление еденицы к количеству правильно отвеченных вопросов пользователем
     */
    @Test
    void addRightNumberOfQuestion() {
        testDataTracker.addData("user3","Информатика",2L);
        testDataTracker.addRightNumberOfQuestion("user3", Boolean.TRUE);
        assertEquals(3, testDataTracker.getRightNumberOfQuestion("user3"));
        testDataTracker.addRightNumberOfQuestion("user3", Boolean.FALSE);
        assertEquals(3, testDataTracker.getRightNumberOfQuestion("user3"));
    }

    /**
     * Проверяет метод удаления пользователя
     */
    @Test
    void RemoveUserTestAndGetSizeOfUsersTest(){
        testDataTracker.addData("user","Информатика",2L);
        assertEquals(1, testDataTracker.getSizeOfUsers());
        testDataTracker.removeUser("user");
        assertEquals(0, testDataTracker.getSizeOfUsers());
    }
    /**
     *Проверяет правильно ли выдается время в миллисекундах, потраченное пользователем на тест
     */
    @Test
    void GetElapsedUserTimeTest() throws InterruptedException {
        testDataTracker.addData("user4","Информатика",2l);
        Thread.sleep(100);
        assertEquals(100, testDataTracker.getElapsedUserTime("user4"), 50);
    }

    /**
     * Проверяет метод, который проверяет проходит ли  пользователь тест(правильный вариант)
     */
    @Test
    void CheckUserRightTest(){
        testDataTracker.addData("user5","Информатика",2l);
        assertEquals(Boolean.TRUE,testDataTracker.checkUser("user5"));
    }

    /**
     * Проверяет метод, который проверяет проходит ли  пользователь тест(неправильный вариант)
     */
    @Test
    void CheckUserWrongTest(){
        testDataTracker.addData("user5","Информатика",2l);
        assertEquals(Boolean.FALSE,testDataTracker.checkUser("user6"));
    }

}