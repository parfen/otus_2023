package ru.otus.l12.homework;

import java.util.Map;

public interface Cash {
    public Map<Banknote, Integer> getCash(Integer summa);
    public void putCash(Banknote banknote, Integer count);
}
