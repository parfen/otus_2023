package homework;


import java.util.*;

public class CustomerReverseOrder {

    Stack<Customer> stack = new Stack<>();
    public void add(Customer customer) {
        stack.add(customer);
    }

    public Customer take() {
        return stack.pop();
    }

}
