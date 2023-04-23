package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class CheckSumma {
    private Integer summa;

    CheckSumma(Integer summa){
        this.summa = summa;
    }

    public Map<Banknote, Integer> getBanknoteFromSumma(AtmData atmData){
        if(summa <= 0){
            throw new RuntimeException("The amount cannot be given out");
        }
        if (summa > atmData.getSummaCash()){
            throw new RuntimeException("The amount cannot be given out. There is not so much cash in the ATM.");
        }
        var separationSumma = new SeparationSumma();
        return separationSumma.separationSumma(summa);
    }
}
