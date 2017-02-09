package relation;

/**
 * Created by innopolis on 05.12.2016.
 */
public class Association {
    public class Programmer {
        private Computer computers[];
        public Programmer() {
        }
    }
    public class Computer {
        private Programmer programmers[];

        public Computer() {
        }
    }
}
