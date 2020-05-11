package Json.Task;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public abstract class Vehicle {
    protected String name;
    protected String owner;
    protected int age;
}