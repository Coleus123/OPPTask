package ru.urfu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест текстовых методов бота
 */
public class BotTextManagerTest {
    BotTextManager botTextManager = new BotTextManager();

    /**
     * Проверяет правильно ли выводит введенный пользователем текст с префиксом
     */
    @Test
    public void testResponseMessageInputPrefix() {
        String InputMessage = "Hello World!";
        String ExpectedMessage = Constants.INPUT_PREFIX + "Hello World!";
        String OutputMessage = botTextManager.ResponseMessage(InputMessage);
        assertEquals(ExpectedMessage, OutputMessage);
    }

    /**
     * Проверяет правильно ли выводит текст при начале разговора с пользователем
     */
    @Test
    public void TestResponseMessageStart() {
        String InputMessage = "/start";
        String ExpectedMessage = Constants.START;
        String OutputMessage = botTextManager.ResponseMessage(InputMessage);
        assertEquals(ExpectedMessage, OutputMessage);
    }

    /**
     * Проверяет правильно ли выводит справку, если попросит пользовватель
     */
    @Test
    public void testResponseMessageHelp() {
        String InputMessage = "/help";
        String ExpectedMessage = Constants.HELP;
        String OutputMessage = botTextManager.ResponseMessage(InputMessage);
        assertEquals(ExpectedMessage, OutputMessage);
    }

}