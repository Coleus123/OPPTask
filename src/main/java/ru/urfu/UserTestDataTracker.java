package ru.urfu;

import java.util.HashMap;
import java.util.Map;



/**
 * Класс хранит данные каждого пользоваетля: номер вопроса, количество правильных ответов, время начала теста
 */
public class UserTestDataTracker {
    private Map<String, UserData> dataMap;

    /**
     * Конструктор для создания экзепляра класса UserTestDataTracker
     */
    public UserTestDataTracker() {
        dataMap = new HashMap<String, UserData>();
    }

    /**
     *Добавляет данные по прохождению теста нового пользователя
     */
    public void addData(String userId, String subject, Long option) {
        UserData userData = new UserData();
        userData.addUserStateExam(subject, option);
        dataMap.put(userId, userData);
    }

    /**
     * Возвращает предмет
     */
    public String getSubject(String userId) {
        return dataMap.get(userId).getSubject();
    }

    /**
     * Возвращает номер варианта
     */
    public Long getOption(String userId) {
        return dataMap.get(userId).getOption();
    }

    /**
     *Возвращает номер вопроса на котором сейчас пользователь
     */
    public Long getNumberOfQuestion(String userId) {
        return dataMap.get(userId).getNumQues();
    }

    /**
     *Возвращает количество правильных отвеченных вопросов пользователем
     */
    public Long getRightNumberOfQuestion(String userId) {
        return dataMap.get(userId).getRightNumQues();
    }

    /**
     *Возвращает время начала теста пользователя
     */
    public Long getUserTime(String userId) {
        return dataMap.get(userId).getStartTime();
    }
    /**
     * Переводит пользователя на следующий вопрос
     */

    public void addNumberOfQuestion(String userId){
        dataMap.get(userId).setRightNumQues(dataMap.get(userId).getNumQues()+1);
    }
    /**
     * В случае правильного ответа добавляет еденицу к количесту правильных ответов
     */
    public void addRightNumberOfQuestion(String userId, Boolean rightAnswer){
        if (rightAnswer){
            dataMap.get(userId).setRightNumQues(dataMap.get(userId).getRightNumQues()+1);
        }
    }

    /**
     *Возвращает время, затраченное пользователем на тест
     */
    public Long getElapsedUserTime(String userId){
        Long ElapsedTime = System.currentTimeMillis() - dataMap.get(userId).getStartTime();
        return ElapsedTime;
    }

    /**
     *Удаляет данные пользователя
     */
    public void removeUser(String userId) {
        dataMap.remove(userId);
    }

    /**
     *Позволяет получить количество пользователей, проходящих тест
     */
    public Integer getSizeOfUsers(){
        return dataMap.size();
    }
    /**
     * Проверяет проходит ли в данный момент пользователь тест
     */
    public Boolean checkUser(String userId){
        return dataMap.containsKey(userId);
    }

}
