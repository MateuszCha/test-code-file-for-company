package CRUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Donald Chump", new Date()));  // id=0
        allPeople.add(Person.createMale("Larry Gates", new Date()));  // id=1
    }

    public static void main(String[] args) throws ParseException{
       switch (args[0])
       {
           case "-c":
               addToList(args);
               break;
           case "-u":
               updateList(args);
               break;
           case "-d":
               deletElement(args);
               break;
           case "-i":
               show(args);
               break;
       }
    }
    public static void addToList(String[] args) {
        addToList(args,allPeople.size());
        System.out.println(allPeople.size()-1);
    }
    public static void addToList(String[] args, int index) {
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("MM dd yyyy");
        try
        {
            date = format.parse(args[3]);
            if(args[2].equals("m")) {
                allPeople.add(index, Person.createMale(args[1], date));
                return;
            }
            if(args[2].equals("f")) {
                allPeople.add(index, Person.createFemale(args[1], date));
                return;
            }
        } catch (ParseException err){
            System.out.println(err);
        }
    }
    public static void updateList(String[] args){
        //-u id name sex bd
        SimpleDateFormat format = new SimpleDateFormat("MM dd yyyy");
        int index = Integer.parseInt(args[1]);
        Person per = allPeople.get(index);
        per.setName(args[2]);
        try {
            per.setBirthDate(format.parse(args[4]));
        }catch(ParseException err) {}
        if(args[3].equals("m"))
            per.setSex(Sex.MALE);
        if(args[3].equals("f"))
            per.setSex(Sex.FEMALE);
        /*
        String[] newArgs = new String[3];

        for(int i = 0; i<newArgs.length; i++)
            newArgs[i] = args[i+1];
        allPeople.remove(index);
        addToList(newArgs,index);
        */

    }
    public static void deletElement(String[] args){
        int index = Integer.parseInt(args[1]);
        Person pers = allPeople.get(index);
        pers.setBirthDate(null);
        pers.setName(null);
        pers.setSex(null);
    }

    public static void show(String[] args){
        //name sex (m/f) bd (format Apr 15 1990))
       //Washington m Apr 15 1990
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        int index = Integer.parseInt(args[1]);
        Person pers = allPeople.get(index);
        String sex = null;
        if(pers.getSex().equals(Sex.MALE))
            sex = "m";
        if(pers.getSex().equals(Sex.FEMALE))
            sex = "f";
        System.out.println(pers.getName() + " " + sex + " " + format.format(pers.getBirthDate()));
    }
}







