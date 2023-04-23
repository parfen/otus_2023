package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class PrintBalanceImpl implements PrintBalance {
    private Map<Banknote, Integer> summaBanknote = new HashMap<>();
    PrintBalanceImpl (Map<Banknote, Integer> summaBanknote){
        this.summaBanknote.putAll(summaBanknote);
    }
    @Override
    public void printBalance(){
        Integer allSum =0;
        for(var banknote : Banknote.values()) {
            Integer countBanknote = this.summaBanknote.get(banknote);
            System.out.println("The remaining banknote in face value " + banknote.name() + " = " + countBanknote.toString());
            Integer sum = countBanknote * banknote.getValue();
            System.out.println("For the amount of " + sum.toString());
            allSum += sum;

        }
        System.out.println("Total amount of funds " + allSum.toString());
    }
}
