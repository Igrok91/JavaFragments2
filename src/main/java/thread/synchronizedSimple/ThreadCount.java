package thread.synchronizedSimple;

/**
 * Created by innopolis on 09.12.2016.
 */
public class ThreadCount extends Thread {
    Service service;
 public ThreadCount(Service s){
     this.service = s;
 }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (service) {
                service.add();
            }

        }
    }
}
