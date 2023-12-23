package ru.otus.l12.homework;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestHomeWork {
    @Test
    public void testGetSum0ReturnError(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(0));
    }
    @Test
    public void testGetSumLessZeroReturnError(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(-1));
    }

    @Test
    public void testGetSum1ReturnError(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(1));
    }
    @Test
    public void testGetSumMoreHaveAtmReturnError(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(2000));
    }

    @Test
    public void testGetSum101ReturnError(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(101));
    }
    @Test
    public void testGetSum100ReturnOneBanknoteHUNDRED(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        List<Banknote> cash = atm.getCash(100);
        // ожидаем 1 купюру
        Assertions.assertEquals(1,cash.size());
        Assertions.assertEquals(Banknote.HUNDRED, cash.get(0));
    }

    @Test
    public void testGetSum200ReturnTwoBanknoteHUNDRED(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        List<Banknote> cash = atm.getCash(200);
        // ожидаем 2 купюры
        Assertions.assertEquals(2,cash.size());
        Assertions.assertEquals(Banknote.HUNDRED, cash.get(0));
        Assertions.assertEquals(Banknote.HUNDRED, cash.get(1));
    }
    @Test
    public void testGetSum105ReturnTwoBanknoteHungredAndFive(){
        ATM atm = new ATM(new Balance());
        initAtm(atm);
        List<Banknote> cash = atm.getCash(105);
        // ожидаем 2 купюры
        Assertions.assertEquals(2,cash.size());
        Assertions.assertTrue(cash.contains(Banknote.HUNDRED));
        Assertions.assertTrue(cash.contains(Banknote.FIVE));
    }

    public void initAtm(ATM atm){
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
