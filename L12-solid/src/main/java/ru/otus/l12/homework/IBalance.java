package ru.otus.l12.homework;

import java.util.List;
import java.util.Map;

public interface IBalance {

    public void addCash(List<Banknote> banknotes);
    public List<Banknote> getCash(List<Banknote> banknotes);
    public Map<Banknote, Integer> getCountBanknote();
    public Integer getSummaCash();
}
