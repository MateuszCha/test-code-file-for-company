package Before.car;

public class Sedan extends Car {
    private static final int MAX_SEDAN_SPEED = 120;

    public Sedan(int numberOfPassengers){
        super(0,numberOfPassengers);

    }
    @Override
    public int getMaxSpeed(){
        return Sedan.MAX_SEDAN_SPEED;
    }

}
