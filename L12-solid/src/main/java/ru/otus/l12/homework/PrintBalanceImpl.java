package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class PrintBalanceImpl implements PrintBalance {
    private IBalance balance;
    PrintBalanceImpl (IBalance balance){
        this.balance = balance;
    }
    @Override
    public void printBalance(){
        Map<Banknote, Integer> summaBanknote = new HashMap<>();
        summaBanknote.putAll(balance.getCountBanknote());
        for(var banknote : Banknote.values()) {
            Integer countBanknote = summaBanknote.get(banknote);
            System.out.println("The remaining banknote in face value " + banknote.name() + " = " + countBanknote.toString());
            Integer sum = countBanknote * banknote.getValue();
            System.out.println("For the amount of " + sum.toString());

        }
        System.out.println("Total amount of funds " + balance.getSummaCash());
    }
}
