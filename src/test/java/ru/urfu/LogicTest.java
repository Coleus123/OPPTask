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
        assertEquals("«Чему равен угол равностороннего треугольника?»", logic.ResponseMessage("/startTest", "user6"));
    }

    /**
     * Проверяет, правильно ли выдаются результаты теста
     */
    @Test
    public void testResponseMessageRightTest() throws InterruptedException {
        logic.ResponseMessage("/startTest", "user6");

        logic.ResponseMessage("60", "user6");
        logic.ResponseMessage("париж", "user6");
        logic.ResponseMessage("100", "user6");
        logic.ResponseMessage("299792458", "user6");
        logic.ResponseMessage("Юпитер", "user6");
        logic.ResponseMessage("2", "user6");
        logic.ResponseMessage("Александр Грейам Белл", "user6");
        logic.ResponseMessage("Au", "user6");
        logic.ResponseMessage("1969", "user6");
        Thread.sleep(100);
        assertEquals("Тест завершен за 0 секунд." +
                " Правильное количество ответов - 10/10. Пройти тест заново или выйти?", logic.ResponseMessage("Тихий", "user6"));
    }

    /**
     * Проверяет, правильно ли подсчитываются результаты
     */
    @Test
    public void testResponseMessageWrongTest() throws InterruptedException {
        logic.ResponseMessage("/startTest", "user6");

        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        logic.ResponseMessage("", "user6");
        Thread.sleep(1010);
        assertEquals("Тест завершен за 1 секунд." +
                        " Правильное количество ответов - 0/10. Пройти тест заново или выйти?",
                logic.ResponseMessage("", "user6"));
    }
}