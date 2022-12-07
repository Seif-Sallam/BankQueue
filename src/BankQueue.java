import java.util.concurrent.ArrayBlockingQueue;

public class BankQueue extends Thread
{
    private CustomerDayEntry allCustomers;
    private ArrayBlockingQueue<Customer> customerArrayList;
    private TimeThread timeThread;

    private int allServed = 0;

    private int allRemoved = 0;

    int capacity;
    public BankQueue(TimeThread timeThread, int capacity, int allPossibleCustomers)
    {
        allCustomers = new CustomerDayEntry();
        this.timeThread = timeThread;
        customerArrayList = new ArrayBlockingQueue<>(capacity);
        allCustomers.init(allPossibleCustomers);
        this.capacity = capacity;
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
            Customer current = customerArrayList.peek();
            for (var customer : customerArrayList) {
                if (current.isVIP() && customer.isVIP()) {
                    if (current.getArrivalTime() > customer.getArrivalTime()) {
                        current = customer;
                    }
                }
                else if(!current.isVIP() && customer.isVIP())
                {
                    current = customer;
                }
                else {
                    if (current.getArrivalTime() > customer.getArrivalTime()) {
                        current = customer;
                    }
                }
            }
            allRemoved++;
            customerArrayList.remove(current);
            return current;
        }
    }

    @Override
    public void run() {
        while(true) {
            while (customerArrayList.size() != capacity) {
                Customer nextCustomer = allCustomers.getNext(timeThread.getTime());
                if (nextCustomer != null) {
                    allServed++;
                    System.out.printf("Customer %s is now into the queue and has the number: %dM and has %d in line\n", nextCustomer.toString(), allServed, allServed - allRemoved);
                    addCustomer(nextCustomer);
                }
            }
        }
    }

    public boolean isEmpty()
    {
        return this.customerArrayList.size() == 0 && allCustomers.isEmpty();
    }

}
