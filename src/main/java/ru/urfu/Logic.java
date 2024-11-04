package ru.urfu;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Класс, который работает с текстом для бота
 */
public class Logic {
    private UserTestDataTracker userTestDataTracker;
    private SubjectManager subjectManager;
    private Map<String, List<QuesAns>> subjects;

    public Logic() {
        userTestDataTracker = new UserTestDataTracker();
        subjectManager = new SubjectManager(Paths.get("Test"));
        subjects = subjectManager.getSubjects();
    }

    /**
     * Возвращает текст после обработки
     */
    public String ResponseMessage(String text, String userId) {
        switch (text) {
            case "/exitTest":
                if (userTestDataTracker.CheckUser(userId)) {
                    userTestDataTracker.RemoveUser(userId);
                    return Constants.EXIT_TEST;
                } else {
                    return Constants.EXIT_TEST_FALSE;
                }

            case "/startTest":
                if (userTestDataTracker.CheckUser(userId)) {
                    userTestDataTracker.RemoveUser(userId);
                }

                userTestDataTracker.AddData(userId, 0, 0, System.currentTimeMillis());
                String firstSubject = subjects.keySet().iterator().next();
                return "Вы выбрали предмет: " + firstSubject + ".";

            case "/start":
                return Constants.START;

            case "/help":
                return Constants.HELP;

            default:
                if (userTestDataTracker.CheckUser(userId)) {
                    String subjectName = "Математика"; // Hardcoded for example, ideally get from user input
                    QuesAns currentQuestionSet = subjects.get(subjectName).get(0);
                    String userAnswer = text.trim();

                    userTestDataTracker.AddRightNumberOfQuestion(userId,
                            userAnswer.equalsIgnoreCase(currentQuestionSet.getAnswer(
                                    userTestDataTracker.GetNumberOfQuestion(userId))));
                    userTestDataTracker.AddNumberOfQuestion(userId);

                    if (userTestDataTracker.GetNumberOfQuestion(userId) == currentQuestionSet.getNumberOfQuestions()) {
                        long elapsedTime = userTestDataTracker.GetElapsedUserTime(userId);
                        String result = "Тест завершен за " + elapsedTime / 1_000 + " секунд. " +
                                "Правильных ответов: " + userTestDataTracker.GetRightNumberOfQuestion(userId) +
                                "/" + currentQuestionSet.getNumberOfQuestions() + ". " +
                                "Пройти тест заново или выйти?";
                        userTestDataTracker.RemoveUser(userId); // Optionally remove user data after completion
                        return result;
                    } else {
                        String nextQuestion = currentQuestionSet.getQuestion(userTestDataTracker.GetNumberOfQuestion(userId));
                        return nextQuestion;
                    }
                } else {
                    return Constants.INPUT_PREFIX + text;
                }
        }
    }
}
