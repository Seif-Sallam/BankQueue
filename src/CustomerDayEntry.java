import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class CustomerDayEntry {
    private ArrayList<Customer> allCustomers;
    void init(int numOfCustomers)
    {
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < numOfCustomers; i++)
        {
            int arrivalTime = random.nextInt(0, numOfCustomers * 4);
            int workTime = random.nextInt(4, 20);

            Customer c = new Customer();
            c.setArrivalTime(arrivalTime);
            c.setWorkTime(workTime);
            boolean isVIP = random.nextInt(0, 2) == 0;
            if (isVIP)
                c.setVIP();
        }

    }

    Customer getNext(int currentTime)
    {
        if (allCustomers.size() == 0)
            return null;
        for (int i = 0; i < allCustomers.size(); i++)
        {
            Customer c = allCustomers.get(i);
            if (c.getArrivalTime() <=currentTime)
            {
                allCustomers.remove(i);
                return c;
            }
        }
        return null;
    }
}
