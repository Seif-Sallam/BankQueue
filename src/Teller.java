public class Teller {
    private boolean isWorking;

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public Teller()
    {
        isWorking = false;
    }
}
