package Before.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human {
    public static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    protected int course;
    protected int[] size;

    protected boolean isSoldier;

    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    private int bloodType;
    private List<Human> children = new ArrayList<Human>();

    public void setBloodType(int code) {
        bloodType = code;
    }

    public int getBloodType() {
        return bloodType;
    }

    public Human(boolean isSoldier) {
        this.isSoldier = isSoldier;
        this.id = nextId;
        nextId++;
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

    public int getCourse() {
        return course;
    }

    public void live() {
        if (isSoldier)
            fight();
    }

    public void fight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printSize() {
        System.out.println("Height: " + size[0] + " Weight: " + size[1]);
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(this.children);
    }
    public void  addChild(Human human){
        this.children.add(human);
    }
    public void removeChild(Human human){
        this.children.remove(human);
    }
}