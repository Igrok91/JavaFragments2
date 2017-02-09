package relation;

/**
 * Created by innopolis on 05.12.2016.
 */
public class Aggregation {
    public class Fish {
        // определения поля home
        // (ссылка на объект Aquarium)
        private Aquarium home;

        public Fish() {
        }
    }
    // определение класса Aquarium
    public class Aquarium {
        // определения поля inhabitants
        // (массив ссылок на объекты Fish)
        private Fish inhabitants[];
        public Aquarium() {
        }
    }
}
