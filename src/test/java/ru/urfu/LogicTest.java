package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест текстовых методов бота
 */
public class LogicTest {
    Logic logic;

    @BeforeEach
    void setUp() {
        logic = new Logic();
    }

    /**
     * Проверяет правильно ли выводит введенный пользователем текст с префиксом
     */
    @Test
    public void testResponseMessageInputPrefix() {
        String InputMessage = "Hello World!";
        String ExpectedMessage = "Вы ввели: " + "Hello World!";
        String OutputMessage = logic.ResponseMessage(InputMessage, "user1");
        assertEquals(ExpectedMessage, OutputMessage);
    }

    /**
     * Проверяет правильно ли выводит текст при начале разговора с пользователем
     */
    @Test
    public void TestResponseMessageStart() {
        String InputMessage = "/start";
        String ExpectedMessage = "Привет! Я Текстовый бот. Напиши /help, чтобы узнать больше";
        String OutputMessage = logic.ResponseMessage(InputMessage, "user2");
        assertEquals(ExpectedMessage, OutputMessage);

    }

    /**
     * Проверяет правильно ли выводит справку, если попросит пользовватель
     */
    @Test
    public void testResponseMessageHelp() {
        String InputMessage = "/help";
        String ExpectedMessage = "Я Текстовый бот, у меня есть несколько функций:\n" +
                " 1) Я могу отправлять Вам, что Вы ввели в чат \n " +
                "2) Если напишите /help, то я Вам расскажу о себе" +
                "3) Если напишите /startTest, то запустится режим тестирования, чтобы выйти из режима тестирования напишите /exitTest";
        String OutputMessage = logic.ResponseMessage(InputMessage, "user3");
        assertEquals(ExpectedMessage, OutputMessage);
    }

    /**
     * Проверяет, правильно ли выводится текст, если тест еще не начат
     */
    @Test
    public void testResponseMessageExit1() {
        String outputMessage = logic.ResponseMessage("/exitTest", "user4");
        String expectedMessage = "Тест ещё не начат";
        assertEquals(expectedMessage, outputMessage);
    }

    /**
     * Проверяет, правильно ли выводится текст, если идет тест
     */
    @Test
    public void testResponseMessageExit2() {
        logic.ResponseMessage("/startTest", "user5");
        String outputMessage = logic.ResponseMessage("/exitTest", "user5");
        assertEquals("Вы вышли из теста", outputMessage);
    }

    /**
     * Проверяет, правильно ли выводится вопрос при начале теста
     */
    @Test
    public void testResponseMessageStart() {
        assertEquals(null, logic.ResponseMessage("/startTest", "user6"));
    }
}


