package thread.taskV2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor Ryabtsev on 12.12.2016.
 */
public class Service {
    private  static boolean flag = true;
    private int time;
    private int n;
    private   Map<Integer, Integer> map;
  public Service(Map<Integer, Integer> map){
      this.map = new HashMap<>();
  }


    public void add(int number)  {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
            if (map.get(number) >= 2) {
                n = number;
                flag = false;
            }
        } else {
            map.put(number, 1);
        }
        this.time++;
        notify();
    }

    public Map last() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }

    public int getTime() {
        return time;
    }

    public int getN() {
        return n;
    }

    public static boolean isFlag() {
        return flag;
    }
}
