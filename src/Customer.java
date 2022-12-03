public class Customer {
    private boolean isVIP;
    private int ID;
    private int arrivalTime;
    private int workTime;

    Customer()
    {
        isVIP = false;
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

    public void setID(int ID) {
        this.ID = ID;
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
}
