package ru.urfu;

/**
 * Класс, который работает с тексом для бота
 */
public class BotTextManager {
    /**
     * Возвращает текст после обработки
     */
    public String ResponseMessage(String text){
        if(text.equals("/start")){
            return  Constants.START;
        }
        else if(text.equals("/help")){
            return Constants.HELP;

        }
        else{
            return Constants.INPUT_PREFIX + text;
        }

    }
}
