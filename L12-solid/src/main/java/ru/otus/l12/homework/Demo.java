package ru.otus.l12.homework;

public class Demo {
    public static void main(String[] args) {
        ATM atm = new ATM(new AtmData());

        initAtm(atm);

        atm.printBalance();
        atm.getCash(2000);
        atm.printBalance();
    }


    public static void initAtm(ATM atm){
        atm.putCash(Banknote.FIVE, 10);
        atm.putCash(Banknote.TEN, 10);
        atm.putCash(Banknote.TWENTY, 10);
        atm.putCash(Banknote.FIFTY, 10);
        atm.putCash(Banknote.HUNDRED, 10);
    }

}
