package thread.taskV1;

/**
 * Created by innopolis on 12.12.2016.
 * Реализовать программу из 2-х потоков.
 * Один из потоков каждую секунду генерирует случайное число в интервале [0;99].
 * Второй поток раз в пять секунд выводит количество уникальных чисел, сгенерированных первым потоком.
 * После того, как будет сгенерировано все 100 чисел, оба потока должны остановить свое выполнение.

 */
public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Thread t1 = new Thread1(service);
        Thread t2 = new Thread2(service);
        t1.start();
        t2.start();

    }
}
