package ru.urfu;


/**
 * Класс, который работает с тексом для бота
 */
public class Logic {
    private UserTestDataTracker userTestDataTracker;
    public Logic() {
        userTestDataTracker = new UserTestDataTracker();
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
                if(userTestDataTracker.CheckUser(userId)){
                    userTestDataTracker.RemoveUser(userId);
                    userTestDataTracker.AddData(userId, 1,0,System.currentTimeMillis());
                    return QuestionBank.QUES1;
                }
                else{
                    userTestDataTracker.AddData(userId, 1, 0, System.currentTimeMillis());
                    return QuestionBank.QUES1;
                }
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
                    userTestDataTracker.AddRightNumberOfQuestion(userId, text.equalsIgnoreCase(userTestDataTracker.
                            AnswerUser(userTestDataTracker.GetNumberOfQuestion(userId))));
                    userTestDataTracker.AddNumberOfQuestion(userId);
                }
                if(userTestDataTracker.CheckUser(userId) && userTestDataTracker.GetNumberOfQuestion(userId) == 11) {
                    text = "Тест завершен за " + userTestDataTracker.GetElapsedUserTime(userId)/1_000 +
                            " секунд. Правильное количество ответов - " + userTestDataTracker.GetRightNumberOfQuestion(userId) + "/10. " +
                            "Пройти тест заново или выйти?";
                }
                else if(userTestDataTracker.CheckUser(userId)&& !(userTestDataTracker.GetNumberOfQuestion(userId) == 11)) {
                    text = userTestDataTracker.QuestionUser(userTestDataTracker.GetNumberOfQuestion(userId));
                }
                else{
                    text = Constants.INPUT_PREFIX + text;
                }
                return text;
        }

    }
}
