public class TimeThread extends Thread
{
    static public int time;

    public int getTime()
    {
        return time;
    }

    public void run()
    {
        while(true) {
            time++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
