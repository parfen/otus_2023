package ru.otus.l12.homework;

import java.util.List;
import java.util.Map;

public interface IATM {
    public List<Banknote> getCash(Integer summa);
    public void setCash(List<Banknote> banknotes);
}
