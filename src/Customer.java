public class Customer {
    private boolean isVIP;
    private int ID;
    private int arrivalTime;
    private int workTime;

    static private int allIDs = 0;

    Customer()
    {
        isVIP = false;
        ID = allIDs++;
    }


    public int getWorkTime()
    {
        return workTime;
    }

    public void setWorkTime(int t)
    {
        this.workTime = t;
    }

    public int getID() {
        return ID;
    }

    public void setVIP()
    {
        isVIP = true;
    }

    public boolean isVIP()
    {
        return isVIP;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("Customer %d, Arrived at %d, VIP: %s, WorkTime: %d", ID, arrivalTime, (isVIP) ? "Yes" : "No", workTime);
    }
}
