package ru.urfu;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;


/**
 * Класс SubjectManager управляет предметами, вариантами заданий и хранит вопросы и ответы.
 * Считывает вопросы и ответы из текстовых файлов, организованных по предметам.
 */
public class SubjectManager {
    private Map<String, List<OptionData>> subjects;

    public SubjectManager(Path rootPath) {
        this.subjects = new HashMap<>();
        populateData(rootPath);
    }

    public Map<String, List<OptionData>> getSubjects() {
        return subjects;
    }

    public void populateData(Path rootPath) {
        try {

            List<String> subjectFolders = Arrays.asList("Информатика", "Математика", "Русский язык");

            Files.list(rootPath).filter(Files::isDirectory).forEach(subjectPath -> {
                String subjectName = subjectPath.getFileName().toString();
                List<OptionData> options = new ArrayList<>();


                try {
                    Files.list(subjectPath).filter(Files::isDirectory).forEach(optionPath -> {
                        OptionData optionData = new OptionData();


                        Path quesPath = optionPath.resolve("ques");
                        if (Files.exists(quesPath) && Files.isDirectory(quesPath)) {
                            loadTextFiles(quesPath, optionData::addContent);
                        }


                        Path answPath = optionPath.resolve("ans");
                        if (Files.exists(answPath) && Files.isDirectory(answPath)) {
                            loadTextFiles(answPath, optionData::addContent);
                        }


                        Path filesPath = optionPath.resolve("files");
                        if (Files.exists(filesPath) && Files.isDirectory(filesPath)) {
                            loadTextFiles(filesPath, optionData::addContent);
                        }

                        options.add(optionData);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                subjects.put(subjectName, options);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTextFiles(Path folderPath, ContentLoader loader) {
        try {
            Files.list(folderPath)
                    .filter(Files::isRegularFile)
                    .sorted()
                    .forEach(filePath -> {
                        try {
                            String content = Files.readString(filePath);
                            loader.load(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path rootPath = Path.of("C:\\Users\\EDWARD\\Desktop\\oppTask");
        SubjectManager manager = new SubjectManager(rootPath);

    }


    @FunctionalInterface
    private interface ContentLoader {
        void load(String content);
    }
}
