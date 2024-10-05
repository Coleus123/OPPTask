package ru.urfu;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для подсчета времени затраченного на тест
 */
public class TimeCount {
    /**
     * Хранит время начала теста каждого пользователя
     */
    private Map<String, Long> userTimeMap;

    /**
     * Этот конструктор инициализирует новый HashMap, который будет использоваться для хранения времени пользователей.
     */
    public TimeCount() {
        userTimeMap = new HashMap<String, Long>();
    }

    /**
     *Запоминает время начала теста
     */
    public void startTimeUser(String userId){
        userTimeMap.put(userId, System.currentTimeMillis());
    }

    /**
     *Возвращает время, потраченное на тест в миллисекундах
     */
    public Long endTimeUser(String userId){
        Long startTime = userTimeMap.get(userId);
        Long elapsedTime = System.currentTimeMillis() - startTime;
        return elapsedTime;
    }

    /**
     *Перестает отслеживать время начала теста пользователя
     */
    public void removeTimeUser(String userId){
        userTimeMap.remove(userId);
    }

    /**
     *Возвращает количество пользователей начавших тест
     */
    public Integer sizeTimeCount(){
        return userTimeMap.size();
    }
}
