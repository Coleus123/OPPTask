package ru.urfu;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, который подсчитывает правильные ответы каждого пользователя
 */
public class AnswerCounter {
    /**
     * Содержит ключ - id пользователя, значение - количество правильных ответов
     */
    private Map<String, Integer> rightAnswerCounter;

    /**
     *создает новый HashMap, который будет использоваться для хранения количества правильных ответов пользователей.
     */
    public AnswerCounter() {
        rightAnswerCounter = new HashMap<String, Integer>();
    }

    /**
     *Добавляет нового пользователя в HashMap
     */
    public void addUser(String userId){
        rightAnswerCounter.put(userId, 0);
    }
    /**
     *В случае правильного ответа добавляет единицу к количеству правильных ответов пользователя
     */
    public void addCountAnswer(String userId, boolean rightAnswer) {
        if(rightAnswer) {
            rightAnswerCounter.put(userId, rightAnswerCounter.get(userId) + 1);
        }
    }

    /**
     *Возвращает количество правильно отвеченных вопросов пользователем
     */
    public Integer getCountAnswer(String userId) {
        Integer result = rightAnswerCounter.get(userId);
        return result;
    }

    /**
     *Удаляет пользователя из HashMap
     */
    public void removeUser(String userId) {
        rightAnswerCounter.remove(userId);
    }
    /**
     *Возвращает количество пользователей, прорешивающих тест
     */
    public Integer getUserCount() {
        return rightAnswerCounter.size();
    }
}
