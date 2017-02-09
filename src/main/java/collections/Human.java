package collections;

/**
 * Created by innopolis on 06.12.2016.
 */
public class Human {
    private  int age;
    private  String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (age != human.age) return false;
        return name.equals(human.name);

    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + name.hashCode();
        return result;
    }
}
