package ru.urfu;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.security.auth.Subject;
import java.util.*;

/**
 * Класс QuesAns содержит список вопросов и соответствующих ответов.
 * Он предоставляет методы для доступа к вопросам и их ответам.
 */
public class QuesAns {

     List<String> questions;
     List<String> answers;
    /**
     * Конструктор инициализирует списки для вопросов, ответов и файлов.
     */
    public QuesAns() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        File projectDir = new File("ЕГЭ");
        if (!projectDir.exists()) {
            projectDir.mkdirs();
        }
    }

    /**
     * Загружает вопросы, ответы и пути файлов из заданного каталога.
     */
    public void Set(String question,String answer,String filepath){
        questions.add(question);
        answers.add(answer);
    }

    /*
     * Возвращает вопрос по указанному индексу.
     */
    public String getQuestion(int index) {
        return index >= 0 && index < questions.size() ? questions.get(index) : null;
    }

    /**
     * Возвращает ответ по указанному индексу.
     */
    public String getAnswer(int index) {
        return index >= 0 && index < answers.size() ? answers.get(index) : null;
    }

    /**
     * Возвращает количество вопросов.
     */
    public Integer getNumberOfQuestions() {
        return questions.size();
    }

    public void getQuestion(String s) {
    }

    public void getAnswer(String s) {
    }

    public void AddBasicQuestion() {
        ;
    }
}

















