import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Bank Queue -> Just Exists (Fill itself)
        // Teller Has the Bank Queue that runs in the background

        int queueCapacity = 20;
        int numOfTellers = 10;
        int allPossible = 200;

        Scanner scanner = new Scanner(System.in);
        boolean redo = false;
        do {
            System.out.print("What is the bank Queue capacity?: ");
            try {
                queueCapacity = scanner.nextInt();
                if (queueCapacity <= 0)
                    throw new Exception("bad");
            } catch (Exception e) {
                System.out.print("Enter valid data please\n");
                redo = true;
            }
        }while(redo);

        do{
            System.out.print("What is the number of tellers?: ");
            try{
                numOfTellers = scanner.nextInt();
                if (numOfTellers <= 0)
                    throw new Exception("bad");
            } catch (Exception e) {
                System.out.print("Enter valid data please\n");
                redo = true;
            }
        }while(redo);

        do{
            System.out.print("What is the total number of customers in this day?: ");
            try{
                allPossible = scanner.nextInt();
                if (allPossible <= 0)
                    throw new Exception("bad");
            } catch (Exception e) {
                System.out.print("Enter valid data please\n");
                redo = true;
            }
        }while(redo);

        ExecutorService service =  Executors.newCachedThreadPool();
        TimeThread timeThread = new TimeThread();
        BankQueue bankQueue = new BankQueue(timeThread, queueCapacity, allPossible);
        ArrayList<Teller> tellers = new ArrayList<>();

        for (int i = 0; i < numOfTellers; i++)
            tellers.add(new Teller(timeThread, bankQueue));

        service.execute(timeThread);
        service.execute(bankQueue);
        for(var teller : tellers)
            service.execute(teller);

        boolean allTellersFree = false;
        while (!allTellersFree || !bankQueue.isEmpty())
        {
            allTellersFree = true;
            for(var teller : tellers)
                allTellersFree &= !teller.isWorking();
            Thread.sleep(100);
        }
        System.out.println("Finished");
        for(var teller : tellers)
            teller.interrupt();
        timeThread.interrupt();
        bankQueue.interrupt();
        service.shutdown();
        System.exit(0);
    }
}
