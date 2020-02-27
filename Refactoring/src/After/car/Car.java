package After.car;

import java.util.Date;

abstract public class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;
    private boolean driverAvailable;
    private int numberOfPassengers;
    protected Car(int type, int numberOfPassengers){
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }
    protected Car(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public static Car create(int type, int numberOfPassengers) {
        Car car = null;
        if(type == 0)
            car = new Truck(numberOfPassengers);
        if(type == 1)
            car = new Sedan(numberOfPassengers);
        if(type == 2)
            car = new Cabriolet(numberOfPassengers);
        return car;
    }
    private boolean canPassengersBeCarried(){
        return (isDriverAvailable() && fuel>0);
    }
    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        return !date.before(summerStart) && !date.after(summerEnd);
    }
    public double getWinterConsumption(int length){
        return (double)winterFuelConsumption*length + winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return (double)summerFuelConsumption*length;
    }

    public void fill(double numberOfGallons) throws Exception {
        if (numberOfGallons < 0)
            throw new Exception();
        fuel += numberOfGallons;
    }
    public double getTripConsumption(Date date, int length, Date summerStart, Date summerEnd) {
        if (isSummer(date, summerStart, summerEnd)) {
            return getSummerConsumption(length);
        } else {
            return getWinterConsumption(length);
        }
    }
    public int getNumberOfPassengersThatCanBeCarried() {
        if (this.canPassengersBeCarried())
            return numberOfPassengers;
        return 0;
    }
    public boolean isDriverAvailable() {
        return driverAvailable;
    }
    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }
    public void startMoving() {
        if (numberOfPassengers > 0)
            fastenPassengerBelts();
        fastenDriverBelt();
    }
    public void fastenPassengerBelts() {
    }
    public void fastenDriverBelt() {
    }
    abstract public int getMaxSpeed();
}