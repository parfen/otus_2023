package ru.otus.l12.homework;

import java.util.*;

public class ATM implements Cash,PrintBalance{
    private AtmData atmData;

    public ATM(AtmData atmData){
        this.atmData = atmData;
    };

    @Override
    public Map<Banknote, Integer> getCash(Integer summa){
        return atmData.getCash(new CheckSumma(summa));
    }


    public void putCash(Banknote banknote, Integer count){
        atmData.putCash(banknote,count);
    }

    @Override
    public void printBalance(){
        var printBalance = new PrintBalanceImpl(atmData.getSummaBanknote());
        printBalance.printBalance();
    }
}
