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
        assertEquals(userData.getSubject(), "Математика");
        assertEquals(userData.getOption(),2);
        assertEquals(userData.getNumQues(), 1l);
        assertEquals(userData.getRightNumQues(), 0l);
        Thread.sleep(100);
        assertEquals(System.currentTimeMillis() - userData.getStartTime(),
                100, 50);
    }

    /**
     * Проверяет праивльно ли присваеивается вариант
     */
    @Test
    public void setOptionTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setOption(2l);
        assertEquals(userData.getOption(), 2l);
    }
    /**
     * Проверяет праивльно ли присваивается номер вопроса
     */
    @Test
    public void setNumQuesTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setNumQues(5l);
        assertEquals(userData.getNumQues(), 5l);
    }

    /**
     * Проверяет праивльно ли присваивается количество правильных ответов
     */
    @Test
    public void setRightNumQuesTest(){
        userData.addUserStateExam("Информатика", 3l);
        userData.setRightNumQues(5l);
        assertEquals(userData.getRightNumQues(), 5l);
    }
}
