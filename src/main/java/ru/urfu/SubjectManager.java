package ru.urfu;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Класс SubjectManager управляет предметами, вариантами заданий и хранит вопросы и ответы.
 * Считывает вопросы и ответы из текстовых файлов, организованных по предметам.
 */
public class SubjectManager {
    private Map<String, List<QuesAns>> subjects;

    public SubjectManager(Path rootPath) {
        this.subjects = new HashMap<>();
        populateData(rootPath);
    }


    /**
     * Возвращает карту предметов и вариантов.
     */
    public Map<String, List<QuesAns>> getSubjects() {
        return subjects;
    }

    public QuesAns getVariant(String subjectName, int variantNumber) {

        List<QuesAns> variants = subjects.get(subjectName);

        if (variants != null && variantNumber >= 0 && variantNumber < variants.size()) {
            return variants.get(variantNumber);
        }

        return null;
    }


    /**
     * Загружает данные по каждому предмету и варианту, организованному в папках.
     */
    public void populateData(Path rootPath) {
        try {
            // Предполагаем, что корневой каталог содержит папки с названиями предметов
            Files.list(rootPath)
                    .filter(Files::isDirectory)
                    .forEach(subjectPath -> {
                        String subjectName = subjectPath.getFileName().toString();
                        List<QuesAns> options = new ArrayList<>();

                        try {
                            // В каждой папке предмета ищем подкаталоги, которые представляют варианты
                            Files.list(subjectPath)
                                    .filter(Files::isDirectory)
                                    .forEach(optionPath -> {
                                        QuesAns optionData = new QuesAns();

                                        // Загружаем вопросы, ответы и файлы из соответствующих папок варианта
                                        loadFolderContents(optionPath.resolve("ques"), optionData::getQuestion);
                                        loadFolderContents(optionPath.resolve("ans"), optionData::getAnswer);


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

    /**
     * Загружает содержимое текстовых файлов из папки и передает его в соответствующий загрузчик контента.
     */
    private void loadFolderContents(Path folderPath, ContentLoader loader) {
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
    }


    /**
     * Это используется при определении того, как следует динамически загружать контент.
     */
    @FunctionalInterface
    private interface ContentLoader {
        void load(String content);
    }
}
