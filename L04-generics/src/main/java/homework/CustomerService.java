package homework;

import java.util.*;

public class CustomerService {
    private TreeMap<Customer, String> treeMapCustomers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallestEntry = treeMapCustomers.firstEntry();
        if(smallestEntry == null){
            return null;
        }
        Customer smallestCustomer = smallestEntry.getKey();
        return new AbstractMap.SimpleEntry<Customer, String>(new Customer(smallestCustomer.getId(), smallestCustomer.getName(), smallestCustomer.getScores()), treeMapCustomers.get(smallestCustomer));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = treeMapCustomers.higherEntry(customer);
        if (nextEntry == null) {
            return null;
        }
        Customer nextCustomer = nextEntry.getKey();
        return new AbstractMap.SimpleEntry<Customer, String>(new Customer(nextCustomer.getId(), nextCustomer.getName(), nextCustomer.getScores()), treeMapCustomers.get(nextCustomer));
    }

    public void add(Customer customer, String data) {
        treeMapCustomers.put(customer,data);
    }
}
