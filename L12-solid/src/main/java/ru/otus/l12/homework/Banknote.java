package ru.otus.l12.homework;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Banknote {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private Integer value;
    Banknote(Integer value){
        this.value=value;
    }

    public Integer getValue(){
        return value;
    }
}
