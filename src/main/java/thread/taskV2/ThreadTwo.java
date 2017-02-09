package thread.taskV2;


import java.util.Map;

/**
 * Created by Igor Ryabtsev on 12.12.2016.
 */
public class ThreadTwo extends Thread {
    private Service service;
    private Map map;
    public ThreadTwo(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        while (Service.isFlag()) {
            synchronized (service) {
                map = service.last();
                int time = service.getTime();
                if (time % 5 == 0) {
                    System.out.println(map);
                }

            }
        }
        System.out.println("Вывелось 5 раз число " + service.getN());
    }
}
