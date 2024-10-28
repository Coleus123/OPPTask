package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubjectManagerTest {

    /**
     * Тестовый класс для SubjectManager.
     */
    @TempDir
        Path tempDir;
        private Path subjectPath;
        private Path optionPath;
        private Path quesPath;
        private Path answPath;
        private Path filesPath;

    /**
     * Подготовка данных перед каждым тестом.
     */
        @BeforeEach
        public void setUp () throws Exception {

            subjectPath = Files.createDirectory(tempDir.resolve("Math"));
            optionPath = Files.createDirectory(subjectPath.resolve("Algebra"));


            quesPath = Files.createDirectory(optionPath.resolve("ques"));
            answPath = Files.createDirectory(optionPath.resolve("answ"));
            filesPath = Files.createDirectory(optionPath.resolve("files"));


            Files.writeString(quesPath.resolve("question1.txt"), "What is 2+2?");
            Files.writeString(answPath.resolve("answer1.txt"), "4");
            Files.writeString(filesPath.resolve("file1.txt"), "extra content");
        }
    /**
     * Тест для проверки загрузки данных в SubjectManager.
     */
        @Test
        public void testPopulateData () {

            SubjectManager manager = new SubjectManager(tempDir);


            Map<String, List<OptionData>> subjects = manager.getSubjects();


            assertNotNull(subjects, "Subjects map should not be null");
            assertEquals(1, subjects.size(), "Expected 1 subject in the map");
            assertTrue(subjects.containsKey("Math"), "Expected 'Math' as a subject");

            List<OptionData> options = subjects.get("Math");
            assertNotNull(options, "Options list should not be null");
            assertEquals(1, options.size(), "Expected 1 option under 'Math'");

            OptionData optionData = options.get(0);
            assertEquals(1, optionData.questions.size(), "Expected 1 question");
            assertEquals("What is 2+2?", optionData.questions.get(0));

            assertEquals(1, optionData.answers.size(), "Expected 1 answer");
            assertEquals("4", optionData.answers.get(0));

            assertEquals(1, optionData.files.size(), "Expected 1 file");
            assertEquals("extra content", optionData.files.get(0));
        }
    }