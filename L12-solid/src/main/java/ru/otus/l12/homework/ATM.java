package ru.otus.l12.homework;

import java.util.*;

public class ATM {

    private Map<Banknote, Integer> summaBanknote = new HashMap<>();
    private Integer sumCash=0;

    public ATM(){};

    public Map<Banknote, Integer> getCash(Integer summa){
        if(summa <= 0){
            throw new RuntimeException("The amount cannot be given out");
        }
        if (summa > sumCash){
            throw new RuntimeException("The amount cannot be given out. There is not so much cash in the ATM.");
        }

        Map<Banknote, Integer> returnCash = new HashMap<>();
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
            returnCash.put(banknote,countReturnBanknote);
            sum = sum - (countReturnBanknote * banknote.getValue());
        }

        if(sum != 0){
            throw new RuntimeException("The amount cannot be given out");
        }

        for(var banknote : returnCash.keySet()){
            summaBanknote.put(banknote, summaBanknote.get(banknote) - returnCash.get(banknote));
        }
        return returnCash;
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

    public void printBalans(){
        Integer allSum =0;
        for(var banknote : Banknote.values()) {
            Integer countBanknote = summaBanknote.get(banknote);
            System.out.println("The remaining banknote in face value " + banknote.name() + " = " + countBanknote.toString());
            Integer sum = countBanknote * banknote.getValue();
            System.out.println("For the amount of " + sum.toString());
            allSum += sum;

        }
        System.out.println("Total amount of funds " + allSum.toString());
    }
}
