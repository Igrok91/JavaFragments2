package thread.taskV2;

/**
 * Created by Igor Ryabtsev on 12.12.2016.
 */
public class ThreadOne extends Thread {

    private Service service;
    public ThreadOne(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        int max = 100;
        while (Service.isFlag()) {
            int random = (int) (Math.random()*max);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (service) {
                    service.add(random);
            }
        }
    }
}
