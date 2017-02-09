package thread.synchronizedSimple;

/**
 * Created by innopolis on 09.12.2016.
 */
public class ThreadMessage2 extends Thread {

    Service service;
    String message;
    public ThreadMessage2(Service s, String str) {
        this.service =s;
        this.message =str;
    }
    @Override
    public void run() {
           synchronized (service) {
    System.out.println(message);
        }
    }
}
