package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class AtmData {
    private Map<Banknote, Integer> summaBanknote = new HashMap<>();
    private Integer sumCash=0;
    public Map<Banknote, Integer> getSummaBanknote(){
        return summaBanknote;
    }
    public Integer getSummaCash(){
        return sumCash;
    }
    public Map<Banknote, Integer> getCash(CheckSumma summa){
        Map<Banknote, Integer> cash = summa.getBanknoteFromSumma(this);
        updateBalance(cash);
        return cash;
    }
    private void updateBalance(Map<Banknote, Integer> mapCash){
        for(var banknote : mapCash.keySet()){
            summaBanknote.put(banknote, summaBanknote.get(banknote) - mapCash.get(banknote));
        }
    }
    public void putCash(Banknote banknote, Integer count){
        if(count <=0){
            return;
        }
        if(summaBanknote.containsKey(banknote)){
            Integer countBanknote = summaBanknote.get(banknote);
            summaBanknote.put(banknote,countBanknote+count);
        }else{
            summaBanknote.put(banknote,count);
        }
        sumCash = sumCash + (banknote.getValue() * count);
    }
}
