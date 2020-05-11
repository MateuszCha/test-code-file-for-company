package PatternsProject.Temple;

public abstract class DrinkMaker {
    abstract void getRightCup();
    abstract void addIngredients();
    abstract void pour();
    void makeDrink(){
        getRightCup();
        addIngredients();
        pour();
    }

}
