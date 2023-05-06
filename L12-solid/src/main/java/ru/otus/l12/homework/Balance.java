package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balance implements IBalance{

    private Map<Banknote, Integer> summaBanknote = new HashMap<>();
    private Integer sumCash=0;

    @Override
    public void addCash(List<Banknote> banknotes) {
        if(banknotes ==null || banknotes.isEmpty()){
            return;
        }
        for(var banknote : banknotes) {
            if (summaBanknote.containsKey(banknote)) {
                Integer countBanknote = summaBanknote.get(banknote);
                summaBanknote.put(banknote, countBanknote + 1);
            } else {
                summaBanknote.put(banknote, 1);
            }
            sumCash = sumCash + (banknote.getValue());
        }
    }

    @Override
    public List<Banknote> getCash(List<Banknote> banknotes) {
        for(var banknote : banknotes){
            Integer countBanknote = summaBanknote.get(banknote);
            summaBanknote.put(banknote, countBanknote - 1);
            sumCash = sumCash - (banknote.getValue());
        }

        return banknotes;
    }

    @Override
    public Map<Banknote, Integer> getCountBanknote() {
        return summaBanknote;
    }

    @Override
    public Integer getSummaCash() {
        return sumCash;
    }
}
