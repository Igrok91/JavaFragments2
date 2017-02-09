package gc.jit;

import java.util.*;

/**
 * Created by innopolis on 10.01.2017.
 */
public class Main {
    static List<Object> cashe = new LinkedList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("waiting");
        while (scanner.hasNext()) {
            switch (scanner.next()) {
                case "collections":
                    System.out.println("Collectable creating...");
                    createBigObject();
                    System.out.println("collectable created");
                    break;
                case "leekable":
                    System.out.println("Leekebke creating");
                    cashe.add(createBigObject());
                    System.out.println("Leekebke created");
                    break;
            }
        }
    }
    public static Object createBigObject(){
        List<String> stringList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i< 1_000_000; i++){
            stringList.add(random.nextInt() + "i" + i);
        }
        return stringList;
    }
}
