package thread.taskV1;

/**
 * Created by innopolis on 12.12.2016.
 */
public class Thread2 extends Thread {
    private Service service;

    public Thread2(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        while (Service.isFlag()) {
            synchronized (service) {
                  service.waitSet();
                if (service.getTime() % 2 == 0) {
                    System.out.println(Service.getN());
                }
            }
        }
        System.out.println("Success");

    }
}
