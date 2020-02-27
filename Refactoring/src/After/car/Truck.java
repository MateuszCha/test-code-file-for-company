package After.car;

public class Truck extends Car{
    private static final int MAX_TRUCK_SPEED = 80;

    public Truck(int numberOfPassengers){
        super(numberOfPassengers);
    }
    @Override
    public int getMaxSpeed(){
        return Truck.MAX_TRUCK_SPEED;
    }
}
