package ru.otus.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {

    private final Map<K,V> weakHashMap = new WeakHashMap<>();
    private final List<HwListener<K, V>> listeners= new ArrayList<>();

    @Override
    public void put(K key, V value) {
        weakHashMap.put(key,value);
        sendActionListeners(key, value,"put");
    }

    @Override
    public void remove(K key) {
        sendActionListeners(key, weakHashMap.get(key),"remove");
        weakHashMap.remove(key);
    }

    @Override
    public V get(K key) {
        sendActionListeners(key, weakHashMap.get(key),"get");
        return weakHashMap.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    private void sendActionListeners(K key, V value, String action) {
        for(var listener : listeners) {
           listener.notify(key, value, action);
        }
    }
}
