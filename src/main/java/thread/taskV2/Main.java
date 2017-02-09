package thread.taskV2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor Ryabtsev on 12.12.2016.
 * Вариант 2:
 Реализовать программу из 2-х потоков.
 Один из потоков каждую секунду генерирует случайное число в интервале [0;99].
 Второй поток раз в пять секунд выводит количество раз, которое каждое из чисел было сгенерированно.
 После того, как какое-либо из чисел будет сгенерированно не менее, чем 5 раз, оба потока должны остановить свое выполнение.
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Service service = new Service(map);
        Thread t1 = new ThreadOne(service);
        Thread t2 = new ThreadTwo(service);
        t1.start();
        t2.start();
    }
}

