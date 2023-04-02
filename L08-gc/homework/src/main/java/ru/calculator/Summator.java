package ru.calculator;

import java.util.ArrayList;
import java.util.List;

public class Summator {
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;
//  private final List<Data> listValues = new ArrayList<>();
    private int size=0;
    //!!! сигнатуру метода менять нельзя
    public void calc(Data data) {
//      хранение Data в List излишне, по факту используем только количество записей
//      оптимизируем заменив на счетчик
//      listValues.add(data);
        size ++;
        int dataValue =  data.getValue();
        if (size % 6_600_000 == 0) {
            size=0;
        }
        sum += dataValue;

        sumLastThreeValues = dataValue + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = dataValue;
        int sumValue = (sumLastThreeValues * sumLastThreeValues / (dataValue + 1) - sum);
        for (int idx = 0; idx < 3; idx++) {
            someValue += sumValue;
            someValue = Math.abs(someValue) + size;
        }
    }

    public int getSum() {
        return sum;
    }

    public int getPrevValue() {
        return prevValue;
    }

    public int getPrevPrevValue() {
        return prevPrevValue;
    }

    public int getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public int getSomeValue() {
        return someValue;
    }
}
