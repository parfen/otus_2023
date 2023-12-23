package ru.otus.l12.homework;

import java.util.*;

public class ValidateSumma implements  IValidateSumma{
    private Integer summa;

    ValidateSumma(Integer summa){
        this.summa = summa;
    }

    @Override
    public Integer validateSumma(IBalance balance) {
        if(summa <= 0){
            throw new RuntimeException("The amount cannot be given out");
        }
        if (summa > balance.getSummaCash()){
            throw new RuntimeException("The amount cannot be given out. There is not so much cash in the ATM.");
        }
        return summa;
    }
}
