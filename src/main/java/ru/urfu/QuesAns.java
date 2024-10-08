package ru.urfu;

import java.util.ArrayList;
/**
        * Класс QuesAns содержит список вопросов и соответствующих ответов.
        * Он предоставляет методы для доступа к вопросам и их ответам.
        */

public class QuesAns {
    private static ArrayList<String> questions;
    private static ArrayList<String> answers;
    /**
     * Конструктор для инициализации списков вопросов и ответов.
     */

    public QuesAns() {

        questions=new ArrayList<>();
        answers=new ArrayList<>();
    }/**
     * Метод AddBasicQuestion инициализирует список основных вопросов и соответствующих им ответов..
     */
public void AddBasicQuestion(){
        questions.add("Чему равен угол равностороннего треугольника?");
        answers.add("60");

        questions.add("Какая столица Франции?");
        answers.add("Париж");

        questions.add("В каком числе столько же букв, сколько и в его названии?");
        answers.add("100");

        questions.add("Какова скорость света в вакууме (в м/с)?");
        answers.add("299792458");

        questions.add("Какая самая большая планета в нашей Солнечной системе?");
        answers.add("Юпитер");


       questions.add ("Какое наименьшее простое число?");
       answers.add("2");


        questions.add("Кто изобрел телефон?");
        answers.add("Александр Грейам Белл");


        questions.add("Какой химический символ у золота?");
        answers.add("Au");


        questions.add("В каком году первый человек высадился на Луне?");
        answers.add("1969");

        questions.add("Какой океан самый большой на Земле?");
        answers.add("Тихий");
}/**
     * Добавляет новый вопрос и соответствующий ему ответ в список.
     */ public  void addQuestionAndAnswer(String question, String answer) {
        questions.add(question);
        answers.add(answer);
    }

    /**
     * Получает вопрос по указанному индексу.
     * @return Строка вопроса.
     */
    public static String getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    /**
     * Получает ответ по указанному индексу.

     * @return Строка ответа.
     */
    public static String getAnswer(int index) {
        if (index >= 0 && index < answers.size()) {
            return answers.get(index);
        }
        return null;
    }

    /**
     * Получает общее количество вопросов.
     *
     * @return Количество вопросов.
     */
    public static int getNumberOfQuestions() {
        return questions.size();
    }



}





