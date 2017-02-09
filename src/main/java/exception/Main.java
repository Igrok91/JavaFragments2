package exception;

import java.io.FileNotFoundException;

/**
 * Created by innopolis on 07.12.2016.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Parent exceptionThrower = new ExceptionThrower();

        try {
            exceptionThrower.doSome();
        } catch (FileNotFoundException e) {
            System.out.println("catch");
            throw e;
        } finally {
            System.out.println("finally");
            throw new RuntimeException(); // Перетирает предыдущие исключения, использовать тогда еще один try/catch
           // return; // Теряется вся информация об исключениях
        }
        //System.out.println("end");
    }
}
