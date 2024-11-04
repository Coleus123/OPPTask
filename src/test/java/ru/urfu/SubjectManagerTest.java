package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Класс SubjectManagerTest содержит тесты для проверки функциональности класса SubjectManager.
 * Он включает в себя создание временной структуры каталогов для загрузки вопросов и ответов по предметам.
 */
class SubjectManagerTest {

    private SubjectManager subjectManager;
    private Path rootPath;


    /**
     * Метод, выполняемый перед каждым тестом.
     * Создает временную директорию и заполняет её образцами данных для предметов.
     */
    @BeforeEach
    public void setup() throws IOException {

        rootPath = Paths.get("Test");
        Files.createDirectories(rootPath);


        createSubjectDirectory("Информатика", "Ques1.txt", "Ans1.txt");
        createSubjectDirectory("Математика", "Ques2.txt", "Ans2.txt");
        createSubjectDirectory("Русский язык", "Ques3.txt", "Ans3.txt");


        subjectManager = new SubjectManager(rootPath);
    }


    /**
     * Метод для создания директории предмета с вопросами и ответами.
     *
     * @param subjectName название предмета
     * @param questionFile имя файла с вопросами
     * @param answerFile имя файла с ответами
     * @throws IOException если возникла ошибка при создании файлов
     */
    private void createSubjectDirectory(String subjectName, String questionFile, String answerFile) throws IOException {
        Path subjectPath = rootPath.resolve(subjectName);
        Files.createDirectories(subjectPath);


        Files.writeString(subjectPath.resolve(questionFile), "Sample Question for " + subjectName);
        Files.writeString(subjectPath.resolve(answerFile), "Sample Answer for " + subjectName);


        System.out.println("Created question file: " + subjectPath.resolve(questionFile));
        System.out.println("Created answer file: " + subjectPath.resolve(answerFile));
    }

    /**
     * Тест для проверки загрузки данных в SubjectManager.
     * Проверяет, что данные по предметам загружаются корректно.
     */
    @Test
    public void testPopulateData() {
        Map<String, List<QuesAns>> subjects = subjectManager.getSubjects();

        assertNotNull(subjects, "Subjects map should not be null");
        assertEquals(3, subjects.size(), "Expected 3 subjects to be loaded");


        assertTrue(subjects.containsKey("Информатика"), "Информатика should be in subjects map");
        assertTrue(subjects.containsKey("Математика"), "Математика should be in subjects map");
        assertTrue(subjects.containsKey("Русский язык"), "Русский язык should be in subjects map");


        System.out.println("Subjects loaded: " + subjects.keySet());


    }
}
