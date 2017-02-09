package thread.taskV1;

/**
 * Created by innopolis on 12.12.2016.
 */
public class Thread1 extends Thread{
    private Service service;

    public Thread1(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
       while (Service.isFlag()){
           int random = (int)(Math.random() * 100);
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
