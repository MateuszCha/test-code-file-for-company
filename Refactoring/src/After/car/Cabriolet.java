package After.car;

public class Cabriolet extends Car{
    private static final int MAX_CABRIOLET_SPEED = 90;

    public Cabriolet(int numberOfPassengers){
        super(numberOfPassengers);
    }
    @Override
    public int getMaxSpeed(){
        return Cabriolet.MAX_CABRIOLET_SPEED;
    }
}
