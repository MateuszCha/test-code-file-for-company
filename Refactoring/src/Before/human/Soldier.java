package Before.human;

public class Soldier extends Human{
    private boolean isSoldier;


    public Soldier(String name, int age){
        super(name,age);
    }


    public void live() {
            this.fight();
    }
    public void fight() {
    }
}
