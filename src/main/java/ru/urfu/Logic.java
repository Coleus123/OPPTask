package ru.urfu;


import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Класс, который работает с тексом для бота
 */
public class Logic {
    private UserTestDataTracker userTestDataTracker;
    private QuesAns quesAns;
    public Logic() {
        userTestDataTracker = new UserTestDataTracker();

        quesAns = new QuesAns();
        quesAns.AddBasicQuestion();

    }
    /**
     * Возвращает текст после обработки
     */
    public String ResponseMessage(String text, String userId){
        switch (text) {
            case "/exitTest":
                if(userTestDataTracker.CheckUser(userId)) {
                    userTestDataTracker.RemoveUser(userId);
                    return Constants.EXIT_TEST;
                }
                else{
                    return Constants.EXIT_TEST_FALSE;
                }
            case "/startTest":
                if(userTestDataTracker.CheckUser(userId)) {
                    userTestDataTracker.RemoveUser(userId);
                }
                    userTestDataTracker.AddData(userId, 0,0,System.currentTimeMillis());

                    return quesAns.getQuestion(0);
            case "/start":
                if(userTestDataTracker.CheckUser(userId)){
                    userTestDataTracker.RemoveUser(userId);
                }
                return Constants.START;

            case "/help":
                if(userTestDataTracker.CheckUser(userId)){
                    userTestDataTracker.RemoveUser(userId);
                }
                return Constants.HELP;

            default:

                if(userTestDataTracker.CheckUser(userId)) {
                    userTestDataTracker.AddRightNumberOfQuestion(userId,
                            text.equalsIgnoreCase(quesAns.getAnswer(
                                    userTestDataTracker.GetNumberOfQuestion(userId))));
                    userTestDataTracker.AddNumberOfQuestion(userId);
                }
                if(userTestDataTracker.CheckUser(userId) &&
                        userTestDataTracker.GetNumberOfQuestion(userId) == quesAns.getNumberOfQuestions()) {
                    text = "Тест завершен за " + userTestDataTracker.GetElapsedUserTime(userId)/1_000 +
                            " секунд. Правильное количество ответов - " +
                            userTestDataTracker.GetRightNumberOfQuestion(userId) +
                            "/"+quesAns.getNumberOfQuestions()+". " +
                            "Пройти тест заново или выйти?";
                }
                else if(userTestDataTracker.CheckUser(userId)&&
                        !(userTestDataTracker.GetNumberOfQuestion(userId) == quesAns.getNumberOfQuestions())) {
                    text = quesAns.getQuestion(userTestDataTracker.GetNumberOfQuestion(userId));
                }
                else{
                    text = Constants.INPUT_PREFIX + text;
                }
                return text;
        }

    }
}
