package exception;

import java.io.FileNotFoundException;

/**
 * Created by innopolis on 07.12.2016.
 */
public class ExceptionThrower extends Parent {
    @Override
    public void doSome() throws FileNotFoundException{
        throw new FileNotFoundException();
    }
}
