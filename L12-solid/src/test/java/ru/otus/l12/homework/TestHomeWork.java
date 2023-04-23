package ru.otus.l12.homework;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestHomeWork {
    @Test
    public void testGetSum0ReturnError(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(0));
    }
    @Test
    public void testGetSumLessZeroReturnError(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(-1));
    }

    @Test
    public void testGetSum1ReturnError(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(1));
    }
    @Test
    public void testGetSumMoreHaveAtmReturnError(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(2000));
    }

    @Test
    public void testGetSum101ReturnError(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Map<Banknote, Integer> cash = atm.getCash(100);
        // ожидаем 1 купюры
        Assertions.assertThrows(RuntimeException.class,  () -> atm.getCash(101));
    }
    @Test
    public void testGetSum100ReturnOneBanknoteHUNDRED(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Map<Banknote, Integer> cash = atm.getCash(100);
        // ожидаем 1 купюру
        Assertions.assertNotNull(cash.get(Banknote.HUNDRED));
        Assertions.assertEquals(1,cash.get(Banknote.HUNDRED));
    }

    @Test
    public void testGetSum200ReturnTwoBanknoteHUNDRED(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Map<Banknote, Integer> cash = atm.getCash(200);
        // ожидаем 2 купюры
        Assertions.assertEquals(2, cash.get(Banknote.HUNDRED));

    }
    @Test
    public void testGetSum105ReturnTwoBanknoteHungredAndFive(){
        ATM atm = new ATM(new AtmData());
        initAtm(atm);
        Map<Banknote, Integer> cash = atm.getCash(105);
        // ожидаем 2 купюры
        Assertions.assertEquals(2,cash.size());
        Assertions.assertEquals(1,cash.get(Banknote.HUNDRED));
        Assertions.assertEquals(1,cash.get(Banknote.FIVE));
    }

    public void initAtm(ATM atm){
        atm.putCash(Banknote.FIVE, 10);
        atm.putCash(Banknote.TEN, 10);
        atm.putCash(Banknote.TWENTY, 10);
        atm.putCash(Banknote.FIFTY, 10);
        atm.putCash(Banknote.HUNDRED, 10);
    }
}
