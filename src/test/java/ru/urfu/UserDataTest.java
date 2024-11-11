package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {
    private UserData userData;
    @BeforeEach
    public void setUp() throws Exception {
        userData = new UserData();
    }

    /**
     * Проверяет правильно ли добавляется пользователь, проходящий тест
     */
    @Test
    public void addUserStateExamTest() throws InterruptedException {
        userData.addUserStateExam("Математика", 2l);
        assertEquals("Математика", userData.getSubject());
        assertEquals(2,userData.getOption());
        assertEquals(0l,userData.getNumQues());
        assertEquals(0l,userData.getRightNumQues());
        Thread.sleep(100);
        assertEquals(100, System.currentTimeMillis() - userData.getStartTime(), 50);
    }

    /**
     * Проверяет праивльно ли присваеивается вариант
     */
    @Test
    public void setOptionTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setOption(2l);
        assertEquals(2l,userData.getOption());
    }
    /**
     * Проверяет праивльно ли присваивается номер вопроса
     */
    @Test
    public void setNumQuesTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setNumQues(5l);
        assertEquals(5l, userData.getNumQues());
    }

    /**
     * Проверяет праивльно ли присваивается количество правильных ответов
     */
    @Test
    public void setRightNumQuesTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setRightNumQues(5l);
        assertEquals(5l, userData.getRightNumQues());
    }
}
