package thread.taskV1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by innopolis on 12.12.2016.
 */
public class Service {
    private  Set<Integer> set = new HashSet<>();
    private static boolean flag = true;
    private  int time;
    private static int n;


    public void add(int a){
        if (set.add(a)) {
            n++;
        }
        if ( n == 10) {
            flag = false;
        }
        this.time++;
        notify();
    }

    public  void waitSet() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFlag() {
        return flag;
    }

    public int getTime() {
        return time;
    }

    public static int getN() {
        return n;
    }

}
