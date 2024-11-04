package ru.urfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubjectManagerTest {


    public SubjectManager subjectManager;

    Path rootPath = Paths.get("C:\\Users\\EDWARD\\Desktop\\oppTask\\src\\ЕГЭ");
    @BeforeEach
    public void setup(){
        subjectManager =new SubjectManager(rootPath);
    }
    /**
     * Тест для проверки загрузки данных в SubjectManager.
     */
        @Test
        public void testPopulateData() {

            Path rootPath = Paths.get("C:\\Users\\EDWARD\\Desktop\\oppTask\\src\\ЕГЭ");


            SubjectManager subjectManager = new SubjectManager(rootPath);
            Map<String, List<QuesAns>> subjects = subjectManager.getSubjects();

            assertNotNull(subjects);
            assertEquals(3, subjects.size(), "Ожидается загрузка 3 предметов");

            assertTrue(subjects.containsKey("Информатика"));
            assertTrue(subjects.containsKey("Математика"));
            assertTrue(subjects.containsKey("Русский язык"));


            List<QuesAns> mathOptions = subjects.get("Математика");
            assertNotNull(mathOptions);
            assertFalse(mathOptions.isEmpty());

        }
    }
