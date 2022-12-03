import java.util.ArrayList;

public class BankQueue {
    private ArrayList<Customer> customerArrayList;

    public BankQueue()
    {
        customerArrayList = new ArrayList<>();
    }

    public void addCustomer(Customer c)
    {
        customerArrayList.add(c);
    }

    Customer getNext()
    {
        if (customerArrayList.size() == 0)
            return null;
        synchronized (this) {
            Customer current = customerArrayList.get(0);
            for (var customer : customerArrayList) {
                if (current.isVIP() && customer.isVIP()) {
                    if (current.getArrivalTime() > customer.getArrivalTime())
                        current = customer;
                }
            }
            return current;
        }
    }


}
