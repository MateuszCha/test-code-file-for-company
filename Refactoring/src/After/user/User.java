package After.user;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private Job job;
    private boolean male;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public void printInfo(){
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);

    }
    public void printAdditionalInfo() {
        if (this.age<16)
            System.out.println("User is younger than 16");
        else
            System.out.println("User is at least 16");
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getCountry() {
        return address.getCountry();
    }
    public String getCity() {
        return address.getCity();
    }
    public Job getJob() {
        return job;
    }
    public boolean isMale() {
        return male;
    }
    public String getAddress() {
        return address.getCountry() + " " + address.getCity() + " " + address.getHouse();
    }
    public String getBoss(){
        return this.job.getBoss();
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setCountry(String country) {
        address.setCountry(country);
    }
    public void setCity(String city) {
        address.setCity(city);
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public void setMale(boolean male) {
        this.male = male;
    }
}


