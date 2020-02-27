package After.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    protected Size size;
    private BloodType bloodType;
    private List<Human> children = new ArrayList<Human>();

    public class Size {
        public int height;
        public int weight;
        Size(int height, int weight){
            this.height =height;
            this.weight = weight;
        }
    }
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
    public BloodType getBloodType() {
        return this.bloodType;
    }
    public Human(String name, int age){
        this.age =age;
        this.name = name;
        this.id = nextId;
        nextId++;
    }
    public void live() {
        ((Soldier)this).fight();
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



    public int getId() {
        return id;
    }

   // public void setId(int id) {
  //      this.id = id;
  //  }

    public void printSize() {
        System.out.println("Height: " + size.height+ " Weight: " + size.weight);
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
    public String getPosition(){
        return "Person";
    }
    public void printData(){
        System.out.println(getPosition()+ ": "+ name);
    }
}