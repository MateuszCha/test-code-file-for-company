package Json.Task;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* 
Using JSON to convert from one class to another
REQUIRED CONNECTED LIBRARIES: Jackson Core, Bind and Annotation 2.8.1

Add JSON annotations so that the main method produces the following:
{
"className" : ".ParkingLot",
"name" : "Super Parking",
"city" : "San Francisco",
"vehicles" : [ {
"className" : "com.codegym.task.task33.task3305.RaceBike",
"name" : "Simba",
"owner" : "Peter",
"age" : 2
}, {
"className" : "com.codegym.task.task33.task3305.Motorbike",
"name" : "Manny",
"owner" : null
}, {
"className" : "com.codegym.task.task33.task3305.Car"
} ]
}

*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ParkingLot parkingLot = new ParkingLot("Super ParkingLot", "San Francisco");
        RacingBike racingBike = new RacingBike("Simba", "Peter", 2);
        Motorbike motorbike = new Motorbike("Manny");
        Car car = new Car();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(racingBike);
        vehicles.add(motorbike);
        vehicles.add(car);
        parkingLot.setVehicles(vehicles);
        convertToJson(parkingLot);
    }

    public static void convertToJson(ParkingLot parkingLot) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, parkingLot);
        System.out.println(writer.toString());
    }
}