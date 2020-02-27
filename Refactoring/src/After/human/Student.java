package After.human;

import java.util.Date;

public class Student extends UniversityPerson {
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name,age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public void live() {
        learn();
    }
    public void learn() {
    }
   public void incAverageGrade(double delta){
       setAverageGrade(getAverageGrade()+delta);
    }
    public void setAverageGrade(double value){
        this.averageGrade = value;
    }
    public void setCourse(double value){
        this.course = (int)value;
    }
    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }
    public void setEndOfSession(Date date){
        endOfSession = date;
    }
    public double getAverageGrade() {
        return averageGrade;
    }
    public int getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
    }
    @Override
    public String getPosition(){
        return "Student";
    }
}
