import java.util.ArrayList;

public class PCSystem {
    private BankQueue queue;
    private ArrayList<Teller> tellers;

    private int numOfTellers;

    public int getNumOfTellers() {
        return numOfTellers;
    }

    public void setNumOfTellers(int numOfTellers) {
        this.numOfTellers = numOfTellers;
        this.tellers.clear();
        for (int i = 0 ; i < numOfTellers; i++)
            tellers.add(new Teller());
    }

    public PCSystem(int numOfTellers)
    {
        tellers = new ArrayList<>();
        setNumOfTellers(numOfTellers);
    }

    public void tellerConsume() throws InterruptedException
    {
        while (true)
        {
            synchronized (this)
            {
                while(allTellersWorking())
                    wait();

                // Consume


                notify();
            }
        }

    }


    public void customerProduce()
    {
        // Produce i.e. add more customers to the bank queue when possible.
    }

    public boolean allTellersWorking()
    {
        boolean flag = true;
        for(var e : tellers)
        {
            flag &= e.isWorking();
        }
        return flag;
    }


}
