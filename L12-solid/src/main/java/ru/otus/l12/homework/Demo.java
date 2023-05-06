package ru.otus.l12.homework;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ATM atm = new ATM(new Balance());

        initAtm(atm);

        atm.printBalance();
        atm.getCash(1000);
        atm.printBalance();
    }


    public static void initAtm(ATM atm){
        List<Banknote> banknoteList = new ArrayList<>();
        for(int i=0; i<10;i++) {
            banknoteList.add(Banknote.FIVE);
            banknoteList.add(Banknote.TEN);
            banknoteList.add(Banknote.TWENTY);
            banknoteList.add(Banknote.FIFTY);
            banknoteList.add(Banknote.HUNDRED);
        }
        atm.setCash(banknoteList);
    }

}
