package classLoader.example2;

import java.io.File;

/**
 * Created by innopolis on 23.12.2016.
 * риложение будет представлять собой каркас движка для динамической загрузки
 * кода в JRE и его исполнения.
 * Каждый модуль будет представлять собой один Java класс, реализующий интерфейс Module.
 * Общий для всех модулей интерфейс необходим для их инвокации.
 * Здесь, важно понимать, что существует еще один способ
 * исполнения динамического кода — Java Reflection API.
 */
public class ModuleEngine {
    public static void main(String args[]) {
        String modulePath = args[0];
        /**
         * Создаем загрузчик модулей.
         */
        ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

        /**
         * Получаем список доступных модулей.
         */
        File dir = new File(modulePath);
        String[] modules = dir.list();

        /**
         * Загружаем и исполняем каждый модуль.
         */
        for (String module: modules) {
            try {

                String moduleName = module.split(".class")[0];
                System.out.println(moduleName);
                Class clazz = loader.loadClass(moduleName);
                Module execute = (Module) clazz.newInstance();

                execute.load();
                execute.run();
                execute.unload();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }
}
