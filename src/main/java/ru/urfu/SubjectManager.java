package ru.urfu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Хранит названия предметов и варианты к ним
 */
public class SubjectManager {
    Map<String, List<QuesAns>> subjects;

    public SubjectManager() {
        subjects = new HashMap<>();
    }
    /**
     * Возвращает количество вариантов в предмете
     */
    public int quantityVariants(String subject) {
        return subjects.get(subject).size();
    }
    /**
     * Возвращает список предметов
     */
    public List<String> allSubjects(){
        List<String> allSubjects = new ArrayList<>();
        for (String key : subjects.keySet()) {
            allSubjects.add(key);
        }
        return allSubjects;
    }
    /**
     * Возвращает вариант по номеру и предменту
     */
    public QuesAns getVariant(String subject, int number){
        if (subjects.get(subject).size() >= number && number > 0) {
            return subjects.get(subject).get(number - 1);
        }
        return null;
    }

    /**
     * Пополняет хешмап названиями предметов и вариантами по указанному пути
     * @param path
     */
    public void populateData(String path){
        File files = new File(path);
        if (!files.exists()){
            files.mkdir();
            File gitkeepFile = new File(files, ".gitkeep");
            try {
                gitkeepFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        File[] filesPath = files.listFiles();
        List<File> subject = new ArrayList<>();
        if (files != null) {
            for (File file : filesPath) {
                if (file.isDirectory()) { // является ли элемент папкой
                    subject.add(file); // добавляем только папки
                }
            }
        }
        else{
            return;
        }
        for (File file : subject) {
            subjects.put(file.getName(), new ArrayList<QuesAns>());
        }
        for (File sub : subject) {
            File[] variants = sub.listFiles();
            for (File variant : variants) {
                if (variant.isDirectory()) {
                    List<String> ques = new ArrayList<>();
                    List<String> answ = new ArrayList<>();
                    File[] variantCheck = variant.listFiles();
                    for (File data : variantCheck) {
                        if (data.isDirectory() && data.getName().equals("ques")) {
                            File[] dataCheck = data.listFiles();
                            Arrays.sort(dataCheck, (f1, f2) -> {
                                int num1 = Integer.parseInt(f1.getName().split("\\.")[0]);
                                int num2 = Integer.parseInt(f2.getName().split("\\.")[0]);
                                return Integer.compare(num1, num2);
                            });
                            for (File dataCheckTxt : dataCheck) {
                                if (!dataCheckTxt.getName().equals(".gitkeep")) {
                                    try {
                                        String content = new String(Files.readAllBytes(dataCheckTxt.toPath()));
                                        ques.add(content);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                        if (data.isDirectory() && data.getName().equals("answ")) {
                            File[] dataCheck = data.listFiles();
                            Arrays.sort(dataCheck, (f1, f2) -> {
                                int num1 = Integer.parseInt(f1.getName().split("\\.")[0]);
                                int num2 = Integer.parseInt(f2.getName().split("\\.")[0]);
                                return Integer.compare(num1, num2);
                            });
                            for (File dataCheckTxt : dataCheck) {
                                if (!dataCheckTxt.getName().equals(".gitkeep")) {
                                    try {
                                        String content = new String(Files.readAllBytes(dataCheckTxt.toPath()));
                                        answ.add(content);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                    }
                    QuesAns quesAns = new QuesAns();
                    for (int i = 0; i < ques.size(); i++) {
                        quesAns.addQuestionAndAnswer(ques.get(i), answ.get(i));
                    }
                    subjects.get(sub.getName()).add(quesAns);
                }
            }
        }
    }
}
