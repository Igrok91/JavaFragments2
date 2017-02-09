package exception;

import java.io.FileNotFoundException;

/**
 * Created by innopolis on 07.12.2016.
 */
public class Parent implements  Comparable<Integer>{
    public void doSome() throws FileNotFoundException {

    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
