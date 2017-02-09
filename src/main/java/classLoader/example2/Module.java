package classLoader.example2;

/**
 * Created by innopolis on 23.12.2016.
 * Данный код представляет собой API для разработки модулей.
 * Его можно скомпилировать отдельно и упаковать в *.jar для поставки отдельно
 * от основного приложения.
 */
public interface Module {
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = 1;

    public void load();
    public int run();
    public void unload();

}
