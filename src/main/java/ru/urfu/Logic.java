package ru.urfu;


import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который работает с тексом для бота
 */
public class Logic {
    private UserTestDataTracker userTestDataTracker;
    private SubjectManager subjectManager;
    public Logic() {
        userTestDataTracker = new UserTestDataTracker();
        subjectManager = new SubjectManager();
        subjectManager.populateData("ЕГЭ");
    }
    /**
     * Возвращает текст после обработки
     */
    public List<String> ResponseMessage(String text, String userId){
        List<String> output = new ArrayList<>();
        switch (text) {
            case "/exitTest":
                if(userTestDataTracker.checkUser(userId)) {
                    userTestDataTracker.removeUser(userId);
                    output.add(Constants.EXIT_TEST);
                    return output;
                }
                else{
                    output.add(Constants.EXIT_TEST_FALSE);
                    return output;
                }
            case "/startTest":
                String out = "Выберите предмет: ";
                userTestDataTracker.addData(userId, "None", 0L);
                for (int i = 0; i<subjectManager.allSubjects().size(); i++){
                    out += subjectManager.allSubjects().get(i);
                    out += "/";
                }
                output.add(out);
                return output;
            case "/start":
                if(userTestDataTracker.checkUser(userId)){
                    userTestDataTracker.removeUser(userId);
                }
                output.add(Constants.START);
                return output;

            case "/help":
                if(userTestDataTracker.checkUser(userId)){
                    userTestDataTracker.removeUser(userId);
                }
                output.add(Constants.HELP);
                return output;

            default:

                if(userTestDataTracker.checkUser(userId)) {
                    if(userTestDataTracker.getSubject(userId).equals("None")){
                        if (subjectManager.allSubjects().contains(text)) {
                            userTestDataTracker.addData(userId, text,0l);
                            String result = "Введите вариант, всего доступно " +
                                   subjectManager.quantityVariants(text) + " вариантов";
                            output.add(result);
                        }
                        else{
                            String result = "Нет такого предмета. Доступны следующие предметы: ";
                            for (int i = 0; i<subjectManager.allSubjects().size(); i++){
                                result += subjectManager.allSubjects().get(i);
                                result += "/";
                            }
                            output.add(result);
                        }
                        return output;
                    }
                    if(!userTestDataTracker.getSubject(userId).equals("None") &&
                            userTestDataTracker.getOption(userId).equals(0L)){
                        try {
                            int number = Integer.parseInt(text);
                            if(subjectManager.getVariant(userTestDataTracker.getSubject(userId), number) != null){
                                userTestDataTracker.addData(userId,
                                        userTestDataTracker.getSubject(userId), (long) number);
                                output.add(subjectManager.getVariant(
                                        userTestDataTracker.getSubject(userId),
                                        userTestDataTracker.getOption(userId).intValue()).
                                        getQuestion(Math.toIntExact(userTestDataTracker.getNumberOfQuestion(userId))));

                            }
                            else{
                                output.add("Нет такого варианта. Введите снова");
                            }
                        } catch (NumberFormatException e) {
                            output.add("Ошибка: некорректный формат числа");
                        }
                        return output;
                    }
                    String rightAnswer = subjectManager.
                            getVariant(userTestDataTracker.getSubject(userId),
                                    userTestDataTracker.getOption(userId).intValue()).getAnswer(
                                    userTestDataTracker.getNumberOfQuestion(userId).intValue());
                    userTestDataTracker.addRightNumberOfQuestion(userId,
                            text.equalsIgnoreCase(rightAnswer));
                    userTestDataTracker.addNumberOfQuestion(userId);
                    if(text.equalsIgnoreCase(rightAnswer)){
                        output.add(Constants.RIGHT_ANSWER);
                    }
                    else {
                        output.add(Constants.WRONG_ANSWER);
                    }
                    if (userTestDataTracker.getNumberOfQuestion(userId) ==
                            subjectManager.getVariant(userTestDataTracker.getSubject(userId),
                                            userTestDataTracker.getOption(userId).intValue())
                                    .getNumberOfQuestions()){
                        String result = "Тест завершен за " + userTestDataTracker.getElapsedUserTime(userId)/1_000 +
                                " секунд. Правильное количество ответов - " +
                                userTestDataTracker.getRightNumberOfQuestion(userId) +
                                "/"+subjectManager.getVariant(userTestDataTracker.getSubject(userId),
                                        userTestDataTracker.getOption(userId).intValue())
                                .getNumberOfQuestions() + ". " +
                                "Пройти тест заново или выйти?";
                        output.add(result);
                        userTestDataTracker.addNumberOfQuestion(userId);
                    }
                    else if(userTestDataTracker.getNumberOfQuestion(userId) <
                            subjectManager.getVariant(
                                            userTestDataTracker.getSubject(userId),
                                            userTestDataTracker.getOption(userId).intValue())
                                    .getNumberOfQuestions()){
                        String question = subjectManager.getVariant(userTestDataTracker.getSubject(userId),
                                        userTestDataTracker.getOption(userId).intValue())
                                .getQuestion(userTestDataTracker
                                        .getNumberOfQuestion(userId).intValue());
                        output.add(question);
                    }
                    else{
                        output.clear();
                        output.add("Пройти тест заново или выйти?");
                    }
                }
                else{
                    output.add(Constants.INPUT_PREFIX + text);
                }
                return output;
        }

    }
}
