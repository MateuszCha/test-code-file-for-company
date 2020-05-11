package PatternsProject.Temple;

public class LatteMaker extends DrinkMaker {
    @Override
    void getRightCup() {
        System.out.println("Grab a cup for latte");
    }

    @Override
    void addIngredients() {
        System.out.print("Make coffee");
    }

    @Override
    void pour() {
        System.out.print("Fill with foamy milk");
    }
}
