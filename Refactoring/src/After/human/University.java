package After.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<Student>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age= age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student s1 = null;
        for(int i = 0; i<students.size(); i++) {
            if(students.get(i).getAverageGrade() == averageGrade)
                s1 =  students.get(i);
        }
        return s1;
    }
    public Student getStudentWithHighestAverageGrade() {
        Student s1 = students.get(0);
        for(int i = 1; i<students.size(); i++) {
            if(s1.getAverageGrade() < students.get(i).getAverageGrade())
                s1 =  students.get(i);
        }
        return s1;
    }
    public Student getStudentWithLowestAverageGrade(){
        Student s1 = students.get(0);
        for(int i = 1; i<students.size(); i++) {
            if(s1.getAverageGrade() > students.get(i).getAverageGrade())
                s1 =  students.get(i);
        }
        return s1;
    }
    public void expel(Student student){
        students.remove(student);
    }
    public List<Student> getStudents() {
        return students;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
}