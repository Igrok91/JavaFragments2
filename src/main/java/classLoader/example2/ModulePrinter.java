package classLoader.example2;

/**
 * Created by innopolis on 23.12.2016.
 */
public class ModulePrinter implements Module {
    public ModulePrinter() {
    }

    public void load() {
        System.out.println("Module " + this.getClass() + " loading ...");
    }

    public int run() {
        System.out.println("Module " + this.getClass() + " running ...");
        return 0;
    }

    public void unload() {
        System.out.println("Module " + this.getClass() + " inloading ...");
    }

}
