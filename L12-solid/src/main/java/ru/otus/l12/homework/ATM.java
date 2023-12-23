package ru.otus.l12.homework;

import java.util.*;

public class ATM implements IATM, IPrintBalance {
    private IBalance balance;

    public ATM(IBalance balance){
        this.balance = balance;
    };

    @Override
    public List<Banknote> getCash(Integer summa){
        IValidateSumma checkSumma = new ValidateSumma(summa);
        summa = checkSumma.validateSumma(balance);
        ITransformationSummaToListBanknote transformer = new TransformationSummaToListBanknote();
        return balance.getCash(transformer.transformationSumma(summa));
    }

    @Override
    public void setCash(List<Banknote> banknotes){
        balance.addCash(banknotes);
    }

    @Override
    public void printBalance(){
        var printBalance = new PrintBalanceImpl(balance);
        printBalance.printBalance();
    }
}
