package ru.urfu;


/**
 * Класс для хранения трех числовых значений: Integer, Integer, Long
 */
public class CustomValues3 {

        private Integer value1;
        private Integer value2;
        private Long value3;

    /**
     *Конструктор создает объект с 3 переданными переменными
     */
        public CustomValues3(Integer value1, Integer value2, Long value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    /**
     *Получить первое значение
     */
    public Integer getValue1() {
        return value1;
    }
    /**
     *Получить второе значение
     */
        public Integer getValue2() {
        return value2;
    }
    /**
     *Получить третье значение
     */
        public Long getValue3() {
        return value3;
    }

    /**
     * Меняет первое значение
     */
    public void setValue1(Integer value1) {
            this.value1 = value1;
        }
    /**
     * Меняет второе значение
     */
    public void setValue2(Integer value2) {
        this.value2 = value2;
    }
    /**
     * Меняет третье значение
     */
    public void setValue3(Long value3) {
        this.value3 = value3;
    }
}
