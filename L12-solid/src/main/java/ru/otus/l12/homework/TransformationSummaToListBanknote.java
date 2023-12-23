package ru.otus.l12.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransformationSummaToListBanknote implements ITransformationSummaToListBanknote {

    @Override
    public List<Banknote> transformationSumma(Integer summa) {
        List<Banknote> transformationSumma = new ArrayList<>();
        int sum = summa;
        for(var banknote : reversBanknoteValues()) {
            if(sum == 0) {
                break;
            }
            int banknoteValue = banknote.getValue();
            if(sum - banknoteValue < 0) {
                continue;
            }
            int countReturnBanknote = sum/banknoteValue;
            for(int i = 0; i < countReturnBanknote; i++) {
                transformationSumma.add(banknote);
            }
            sum = sum - (countReturnBanknote * banknoteValue);
        }

        if(sum != 0){
            throw new RuntimeException("The amount cannot be given out");
        }
        return transformationSumma;
    }

    private static Banknote[] reversBanknoteValues(){
        Banknote [] reversValue = Banknote.values();
        Arrays.sort(reversValue, Collections.reverseOrder());
        return reversValue;
    }
}
