package ru.urfu;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит данные пользователя
 */
public class UserData {
    private String subject;
    private List<Long> data;

    /**
     * Конструктор
     */
    void UserData(){
        data = new ArrayList<Long>();
    }

    /**
     * Добавляет пользователя, который прохобит пробник по экзамену
     */
    public void addUserStateExam (String subject, Long option){
        this.subject = subject;
        this.data.add(option);
        this.data.add(1l);
        this.data.add(0l);
        this.data.add(System.currentTimeMillis());
    }

    /**
     * Меняет номер вопроса
     */
    public void setOption (Long option){
        this.data.set(0, option);
    }
    /**
     * Меняет номер вопроса
     */
    public void setNumQues(Long numQues) {
            data.set(1, numQues);
    }

    /**
     * Меняет количество правильных ответов
     */
    public void setRightNumQues(Long rightNumQues){
            data.set(2, rightNumQues);
    }

    /**
     * Возвращает предмет
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Возвращает номер варианта
     */
    public Long getOption(){
        return data.get(0);
    }
    /**
     * Возвращает номер вопроса, на котором сейчас пользователь
     */
    public Long getNumQues() {
        return data.get(1);
    }

    /**
     * Возвращает количество праивльно отвеченных вопросов
     */
    public Long getRightNumQues() {
        return data.get(2);
    }

    /**
     * Возвращает время начала теста
     */
    public Long getStartTime(){
            return this.data.get(3);
    }

}
