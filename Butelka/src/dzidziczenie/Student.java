package dzidziczenie;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */
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
