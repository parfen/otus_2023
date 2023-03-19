package homework;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {
    private TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    public Map.Entry<Customer, String> getSmallest() {
        Customer k = map.firstEntry().getKey();
        return new AbstractMap.SimpleEntry<Customer, String>(new Customer(k.getId(), k.getName(), k.getScores()),map.get(k));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> next = map.higherEntry(customer);
        if (next == null)
            return null;
        Customer k = next.getKey();
        return new AbstractMap.SimpleEntry<Customer, String>(new Customer(k.getId(), k.getName(), k.getScores()),map.get(k));
    }

    public void add(Customer customer, String data) {
        map.put(customer,data);
    }
}
