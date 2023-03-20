package homework;


import java.util.*;

public class CustomerReverseOrder {

    private final ArrayDeque<Customer> stackOrder = new ArrayDeque<>();
    public void add(Customer customer) {
        stackOrder.push(customer);
    }

    public Customer take() {
        return stackOrder.pop();
    }

}
