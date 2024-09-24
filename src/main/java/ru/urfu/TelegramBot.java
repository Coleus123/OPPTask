package ru.urfu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс телеграмм-бот
 */
public class TelegramBot extends TelegramLongPollingBot{
    private String botToken;
    private String botName;

    /**
     *Конструктор телеграмм-бот
     */
    public TelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
    }

    /**
     * Отправляет ответ на сообщение пользователя
     */
    @Override
    public void onUpdateReceived(Update update) {
        BotTextManager botTextManager = new BotTextManager();
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(botTextManager.ResponseMessage(text));

        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * возвращает бот-токен
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * Возвращает имя бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }
}