package PatternsProject.Temple;

public class TeaMaker extends DrinkMaker {
    @Override
    void getRightCup() {
        System.out.println("Grab a cup for tea");
    }

    @Override
    void addIngredients() {

        System.out.println("Pour tea");
    }

    @Override
    void pour() {
        System.out.println("Fill with boiling water");
    }
}
