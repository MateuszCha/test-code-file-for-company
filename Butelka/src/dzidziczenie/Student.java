package dzidziczenie;

public class Student extends Osoba
{
    
    Student(String sImie, String sNazwisko, float fWynagrodzenie)
   {
       super(sImie,sNazwisko); 
   }

    @Override
    void opis() 
    {
        System.out.println("Jestem Studentem!!!!");
        System.out.println(this.sImie +" "+this.sNazwisko);
        System.out.println("Wynagrodzenie : brak");
    }
}
