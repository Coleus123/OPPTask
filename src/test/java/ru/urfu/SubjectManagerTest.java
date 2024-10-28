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


    @TempDir
        Path tempDir;
        private Path subjectPath;
        private Path optionPath;
        private Path quesPath;
        private Path answPath;
        private Path filesPath;

        @BeforeEach
        public void setUp () throws Exception {
            // Create a directory structure
            subjectPath = Files.createDirectory(tempDir.resolve("Math"));
            optionPath = Files.createDirectory(subjectPath.resolve("Algebra"));

            // Creating ques, answ, and files directories
            quesPath = Files.createDirectory(optionPath.resolve("ques"));
            answPath = Files.createDirectory(optionPath.resolve("answ"));
            filesPath = Files.createDirectory(optionPath.resolve("files"));

            // Adding mock files to each directory
            Files.writeString(quesPath.resolve("question1.txt"), "What is 2+2?");
            Files.writeString(answPath.resolve("answer1.txt"), "4");
            Files.writeString(filesPath.resolve("file1.txt"), "extra content");
        }

        @Test
        public void testPopulateData () {
            // Initialize SubjectManager with the temp directory
            SubjectManager manager = new SubjectManager(tempDir);

            // Retrieve subjects data
            Map<String, List<OptionData>> subjects = manager.getSubjects();

            // Assertions
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