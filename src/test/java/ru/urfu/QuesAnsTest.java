package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.QuesAns;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты для класса QuesAns.
 */
public class QuesAnsTest {

    private QuesAns quesAns;

    @BeforeEach
    public void setUp() {

        quesAns = new QuesAns();
        quesAns.AddBasicQuestion();
    }

    /**
     * Модульный тест для
     */
    @Test
    public void testAddBasicQuestions() {

        assertEquals(10, QuesAns.getNumberOfQuestions(), "Должно быть 10 основных вопросов.");
    }
    /**
     * Модульный тест для
     */
    @Test
    public void testAddQuestionAndAnswer() {

        quesAns.addQuestionAndAnswer("Сколько дней в високосном году?", "366");


        assertEquals(11, QuesAns.getNumberOfQuestions(), "После добавления одного вопроса должно быть 11.");


        assertEquals("Сколько дней в високосном году?", QuesAns.getQuestion(10));
        assertEquals("366", QuesAns.getAnswer(10));
    }
    /**
     * Модульный тест для извлечения конкретного вопроса и проверки его содержания
     */
    @Test
    public void testGetQuestion() {
        // Retrieve a specific question and check its content
        assertEquals("Чему равен угол равностороннего треугольника?", QuesAns.getQuestion(0));
        assertEquals("Какая столица Франции?", QuesAns.getQuestion(1));
    }
    /**
     * Модульный тест для получения ответов по их индексным номерам
     */
    @Test
    public void testGetAnswer() {

        assertEquals("60", QuesAns.getAnswer(0));
        assertEquals("Париж", QuesAns.getAnswer(1));
    }
    /**
     * Модульный тест для неправильного индексного номера вопроса
     */
    @Test
    public void testInvalidQuestionIndex() {

        assertNull(QuesAns.getQuestion(100), "Запрос вопроса с недействительным индексом должен вернуть null.");
    }
    /**
     * Модульный тест для неправильного индексного номера ответа
     */
    @Test
    public void testInvalidAnswerIndex() {

        assertNull(QuesAns.getAnswer(100), "Запрос ответа с недействительным индексом должен вернуть значение null.");
    }
    /**
     * Модульный тест для получения количества вопросов по умолчанию
     */
    @Test
    public void testGetNumberOfQuestions() {

        assertEquals(10, QuesAns.getNumberOfQuestions(), "Первоначально должно быть 10 вопросов.");


        quesAns.addQuestionAndAnswer("Как называется столица России?", "Москва");
        assertEquals(11, QuesAns.getNumberOfQuestions(), "После добавления вопроса должно быть 11 вопросов.");
    }
}
