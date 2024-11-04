package ru.urfu;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.urfu.QuesAns;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class QuesAnsTest {


    private QuesAns quesAns;

    @Before
    public void setUp() {
        quesAns = new QuesAns();
    }

    @Test
    public void testInitialEmptyState() {
        // Verify that the initial state has no questions
        assertEquals(0, quesAns.getNumberOfQuestions());
        assertNull(quesAns.getQuestion(0));
        assertNull(quesAns.getAnswer(0));
    }

    @Test
    public void testSetMethodWithNoFilepath() {

        quesAns.Set("What is Java?", "Java is a programming language.", "");

        // Verify the question, answer, and "none" filepath are added correctly
        assertEquals(1, quesAns.getNumberOfQuestions());
        assertEquals("What is Java?", quesAns.getQuestion(0));
        assertEquals("Java is a programming language.", quesAns.getAnswer(0));

    }

    @Test
    public void testSetMethodWithFilepath() {

        quesAns.Set("What is Java?", "Java is a programming language.", "src/ЕГЭ/sample.txt");

        // Verify the question, answer, and filepath are added correctly
        assertEquals(1, quesAns.getNumberOfQuestions());
        assertEquals("What is Java?", quesAns.getQuestion(0));
        assertEquals("Java is a programming language.", quesAns.getAnswer(0));
    }

    @Test
    public void testProjectDirectoryCreation() {

        File projectDir = new File("ЕГЭ");
        assertTrue(projectDir.exists(), "Project-relative directory 'ЕГЭ' should exist");
        assertTrue(projectDir.isDirectory(), "ЕГЭ should be a directory");
    }
}

