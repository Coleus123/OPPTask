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
     List<String> files;

    /**
     * Конструктор инициализирует списки для вопросов, ответов и файлов.
     */
    public QuesAns() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    /**
     * Загружает вопросы, ответы и пути файлов из заданного каталога.
     */
    public void loadContentFromDirectory(String directoryPath) {
        File dir = new File("C:\\\\Users\\\\EDWARD\\\\Desktop\\\\oppTask\\\\src\\\\ЕГЭ");
        File[] questionFiles = new File(dir, "ques").listFiles();
        File[] answerFiles = new File(dir, "ans").listFiles();

        if (questionFiles == null || answerFiles == null) {
            System.out.println("Не удалось найти папки вопросов или ответов.");
            return;
        }

        try {
            for (File questionFile : questionFiles) {
                String question = new String(Files.readAllBytes(questionFile.toPath()));
                questions.add(question);
                files.add(questionFile.getAbsolutePath());
            }
            for (File answerFile : answerFiles) {
                String answer = new String(Files.readAllBytes(answerFile.toPath()));
                answers.add(answer);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }

    /**
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
     * Возвращает путь к файлу по указанному индексу.
     */
    public String getFile(int index) {
        return index >= 0 && index < files.size() ? files.get(index) : null;
    }

    /**
     * Возвращает количество вопросов.
     */
    public Integer getNumberOfQuestions() {
        return questions.size();
    }

    public void getQuestion(String s) {
    }

    public void getFile(String s) {
    }

    public void getAnswer(String s) {
    }

    public void AddBasicQuestion() {
        loadContentFromDirectory("C:\\Users\\EDWARD\\Desktop\\oppTask\\src\\ЕГЭ");
    }
}

















