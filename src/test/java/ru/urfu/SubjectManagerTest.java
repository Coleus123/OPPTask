package ru.urfu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.io.File;
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

        rootPath = Files.createTempDirectory("Test");


        createSubjectDirectory("Математика", "Option1", "Ques1.txt", "Ans1.txt");


        subjectManager = new SubjectManager(rootPath);
    }

    /**
     * Метод для создания директории предмета с вопросами и ответами.
     *
     * @param subjectName название предмета
     * @param optionName имя варианта
     * @param questionFile имя файла с вопросами
     * @param answerFile имя файла с ответами
     * @throws IOException если возникла ошибка при создании файлов
     */
    private void createSubjectDirectory(String subjectName, String optionName, String questionFile, String answerFile) throws IOException {
        Path subjectPath = rootPath.resolve(subjectName);
        Files.createDirectories(subjectPath);

        Path optionPath = subjectPath.resolve(optionName);
        Files.createDirectories(optionPath);


        Files.writeString(optionPath.resolve(questionFile), "What is 2 + 2?");
        Files.writeString(optionPath.resolve(answerFile), "4");

        System.out.println("Created question file: " + optionPath.resolve(questionFile));
        System.out.println("Created answer file: " + optionPath.resolve(answerFile));
    }

    /**
     * Тест для проверки загрузки данных в SubjectManager.
     * Проверяет, что данные по предметам загружаются корректно.
     */
    @Test
    public void testPopulateData() {
        Map<String, List<QuesAns>> subjects = subjectManager.getSubjects();

        assertNotNull(subjects, "Subjects map should not be null");
        assertEquals(1, subjects.size(), "Expected 1 subject to be loaded");

        assertTrue(subjects.containsKey("Математика"), "Математика should be in subjects map");

        List<QuesAns> options = subjects.get("Математика");
        assertNotNull(options, "Options for Mathematics should not be null");
        assertEquals(1, options.size(), "Expected 1 option for Mathematics");

        QuesAns quesAns = options.get(0);
        assertNotNull(quesAns, "QuesAns object should not be null");


        assertEquals("What is 2 + 2?", quesAns.getQuestion(0), "Question should match the expected question");
        assertEquals("4", quesAns.getAnswer(0), "Answer should match the expected answer");

        System.out.println("Subjects loaded: " + subjects.keySet());
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Удаляет временную директорию и все её содержимое.
     */
    @AfterEach
    public void cleanup() throws IOException {

        if (rootPath != null) {
            Files.walk(rootPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}
