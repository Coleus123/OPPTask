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
    void addDataTestAndGetNumberOfQuestionTestAndGetRightNumberOfQuestionTestAndGetUserTimeTest() {
        testDataTracker.AddData("user1",1,2,3L);
        assertEquals(1, testDataTracker.GetNumberOfQuestion("user1"));
        assertEquals(2, testDataTracker.GetRightNumberOfQuestion("user1"));
        assertEquals(3L, testDataTracker.GetUserTime("user1"));
    }

    /**
     * Проверяет метод AddNumberOfQuestion на добавление еденицы к номеру вопроса, на котором сейчас пользователь
     */
    @Test
    void addNumberOfQuestion() {
        testDataTracker.AddData("user2",1,2,3L);
        testDataTracker.AddNumberOfQuestion("user2");
        assertEquals(2, testDataTracker.GetNumberOfQuestion("user2"));
    }

    /**
     * Проверяет на добавление еденицы к количеству правильно отвеченных вопросов пользователем
     */
    @Test
    void addRightNumberOfQuestion() {
        testDataTracker.AddData("user3",1,2,3L);
        testDataTracker.AddRightNumberOfQuestion("user3", Boolean.TRUE);
        assertEquals(3, testDataTracker.GetRightNumberOfQuestion("user3"));
        testDataTracker.AddRightNumberOfQuestion("user3", Boolean.FALSE);
        assertEquals(3, testDataTracker.GetRightNumberOfQuestion("user3"));
    }

    /**
     * Проверяет метод удаления пользователя
     */
    @Test
    void RemoveUserTestAndGetSizeOfUsersTest(){
        testDataTracker.AddData("user",1,2,3L);
        assertEquals(1, testDataTracker.GetSizeOfUsers());
        testDataTracker.RemoveUser("user");
        assertEquals(0, testDataTracker.GetSizeOfUsers());
    }
    /**
     *Проверяет правильно ли выдается время в миллисекундах, потраченное пользователем на тест
     */
    @Test
    void GetElapsedUserTimeTest() throws InterruptedException {
        testDataTracker.AddData("user4",1,2, System.currentTimeMillis());
        Thread.sleep(100);
        assertEquals(100, testDataTracker.GetElapsedUserTime("user4"), 50);
    }

    /**
     * Проверяет метод, который проверяет проходит ли  пользователь тест(правильный вариант)
     */
    @Test
    void CheckUserRightTest(){
        testDataTracker.AddData("user5",1,2,3L);
        assertEquals(Boolean.TRUE,testDataTracker.CheckUser("user5"));
    }

    /**
     * Проверяет метод, который проверяет проходит ли  пользователь тест(неправильный вариант)
     */
    void CheckUserWrongTest(){
        testDataTracker.AddData("user5",1,2,3L);
        assertEquals(Boolean.FALSE,testDataTracker.CheckUser("user6"));
    }

    /**
     * Проверяет, правильнй ли вопрос возвращается
     */
    void QuestionUserTestRight(){
        assertEquals("Какая самая большая планета в нашей Солнечной системе?", testDataTracker.QuestionUser(5));;
    }

    /**
     * Проверяет, правильный ли ответ возвращается
     */
    void AnswerUserTestRight(){
        assertEquals("299792458", testDataTracker.AnswerUser(4));
    }

}