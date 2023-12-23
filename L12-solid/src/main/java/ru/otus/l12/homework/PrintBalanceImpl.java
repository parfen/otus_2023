package ru.otus.l12.homework;

import java.util.HashMap;
import java.util.Map;

public class PrintBalanceImpl implements IPrintBalance {
    private IBalance balance;
    PrintBalanceImpl (IBalance balance){
        this.balance = balance;
    }

    @Override
    public void printBalance(){
        if(balance == null){
            System.out.println("Balance is not initialized");
            return;
        }
        for(var banknote : Banknote.values()) {
            Integer countBanknote = balance.getCountBanknote().get(banknote);
            System.out.println("The remaining banknote in face value " + banknote.name() + " = " + countBanknote.toString());
            Integer sum = countBanknote * banknote.getValue();
            System.out.println("For the amount of " + sum.toString());

        }
        System.out.println("Total amount of funds " + balance.getSummaCash());
    }
}
