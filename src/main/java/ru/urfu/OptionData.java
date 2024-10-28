package ru.urfu;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс OptionData хранит вопросы и ответы для одного варианта задания.
 * Содержит методы для добавления вопросов и ответов, а также для их получения.
 */
public class OptionData {
    private List<String> questions;
    private List<String> answers;
    private List<String> files;

    public OptionData() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<String> getFiles() {
        return files;
    }

    public void addQuestion(String question) {
        questions.add(question);
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void addFile(String filePath) {
        files.add(filePath);
    }
}
