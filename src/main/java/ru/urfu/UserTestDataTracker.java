package ru.urfu;

import java.util.HashMap;
import java.util.Map;



/**
 * Класс хранит данные каждого пользоваетля: номер вопроса, количество правильных ответов, время начала теста
 */
public class UserTestDataTracker {
    private Map<String, CustomValues3> dataMap;

    /**
     * Конструктор для создания экзепляра класса UserTestDataTracker
     */
    public UserTestDataTracker() {
        dataMap = new HashMap<String, CustomValues3>();
    }

    /**
     *Добавляет данные по прохождению теста нового пользователя
     */
    public void AddData(String userId, Integer value1, Integer value2, Long value3) {
        CustomValues3 userData = new CustomValues3(value1, value2, value3);
        dataMap.put(userId, userData);

    }
    /**
     *Возвращает номер вопроса на котором сейчас пользователь
     */
    public Integer GetNumberOfQuestion(String userId) {
        return dataMap.get(userId).getValue1();
    }

    /**
     *Возвращает количество правильных отвеченных вопросов пользователем
     */
    public Integer GetRightNumberOfQuestion(String userId) {
        return dataMap.get(userId).getValue2();
    }

    /**
     *Возвращает время начала теста пользователя
     */
    public Long GetUserTime(String userId) {
        return dataMap.get(userId).getValue3();
    }
    /**
     * Переводит пользователя на следующий вопрос
     */

    public void AddNumberOfQuestion(String userId){
        Integer i = dataMap.get(userId).getValue1()+1;
        dataMap.get(userId).setValue1(i);
    }
    /**
     * В случае правильного ответа добавляет еденицу к количесту правильных ответов
     */
    public void AddRightNumberOfQuestion(String userId, Boolean rightAnswer){
        if (rightAnswer){
            Integer i = dataMap.get(userId).getValue2()+1;
            dataMap.get(userId).setValue2(i);
        }
    }

    /**
     *Возвращает время, затраченное пользователем на тест
     */
    public Long GetElapsedUserTime(String userId){
        Long ElapsedTime = System.currentTimeMillis() - dataMap.get(userId).getValue3();
        return ElapsedTime;
    }

    /**
     *Удаляет данные пользователя
     */
    public void RemoveUser(String userId) {
        dataMap.remove(userId);
    }

    /**
     *Позволяет получить количество пользователей, проходящих тест
     */
    public Integer GetSizeOfUsers(){
        return dataMap.size();
    }
    /**
     * Проверяет проходит ли в данный момент пользователь тест
     */
    public Boolean CheckUser(String userId){
        return dataMap.containsKey(userId);
    }

}
