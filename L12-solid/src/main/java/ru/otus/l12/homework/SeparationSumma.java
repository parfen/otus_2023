package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class SeparationSumma {

    public Map<Banknote, Integer> separationSumma (Integer summa){
        Map<Banknote, Integer> separationSumma = new HashMap<>();
        int sum = summa;
        for(var banknote : Banknote.reversValues()) {
            if(sum == 0) {
                break;
            }
            int banknoteValue = banknote.getValue();
            if(sum - banknoteValue < 0) {
                continue;
            }
            int countReturnBanknote = sum/banknoteValue;
            separationSumma.put(banknote,countReturnBanknote);
            sum = sum - (countReturnBanknote * banknote.getValue());
        }

        if(sum != 0){
            throw new RuntimeException("The amount cannot be given out");
        }
        return separationSumma;
    }
}
