package ru.urfu;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.urfu.QuesAns;

import static org.junit.jupiter.api.Assertions.*;
public class QuesAnsTest {


    private QuesAns quesAns;

    @Before
    public void setUp() {
        quesAns = new QuesAns();

        quesAns.loadContentFromDirectory("C:\\Users\\EDWARD\\Desktop\\oppTask\\src\\ЕГЭ");
    }

    @Test
    public void loadContentFromDirectory() {
        assertEquals(0,quesAns.getNumberOfQuestions());
        assertNull(quesAns.getQuestion(0));
        assertNull(quesAns.getAnswer(0));
        assertNull(quesAns.getFile(0));
    }
}

