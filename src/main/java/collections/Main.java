package collections;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by innopolis on 06.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        Map map = new HashMap();
        Human human = new Human(25, "Igor");
        map.put(human, new Car("Audi", 2007));

        human.setAge(30); // Поэтому используется неизменяемые объекты, чтобы не менялся его хеш-код
        Human human2 = getHumanFromDB();
        System.out.println(human.equals(human2));
        System.out.println("Igor's car " + map.get(human));
    }

    public static Human getHumanFromDB() {
        return new Human(25, "Igor");
    }
}
