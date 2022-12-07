public class Teller extends Thread
{
    private boolean isWorking;

    private Customer currentCustomer;

    private BankQueue bankQueue;
    private TimeThread tThread;

    static private int allIDs = 0;

    private int id;

    private int endTime = 0;

    public boolean isWorking() {
        return isWorking;
    }

    public Teller(TimeThread tThread, BankQueue bankQueue)
    {
        this.tThread = tThread;
        this.bankQueue = bankQueue;
        isWorking = false;
        id = allIDs++;
    }

    private void addCustomer(Customer c)
    {
        endTime = tThread.getTime() + c.getWorkTime();
        isWorking = true;
        currentCustomer = c;
    }

    public void run()
    {
        while(true) {
            if (tThread.getTime() >= endTime) {
                if (currentCustomer != null)
                    System.out.printf("Teller: %d has finished with customer: %s\n", id, currentCustomer.toString());
                currentCustomer = null;
                Customer c = bankQueue.getNext();
                isWorking = false;
                if (c != null) {
                    System.out.printf("Teller: %d is now serving: %s\n", id, c.toString());
                    addCustomer(c);
                }
            }

        }
    }

}
