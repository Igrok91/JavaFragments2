package thread.synchronizedSimple;

/**
 * Created by innopolis on 09.12.2016.
 */
public class Service  {
    private volatile int time;

    public void add() {
        this.time++;
        notify();
    }
    public int last() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.time;
    }



}
