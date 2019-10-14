package dzidziczenie;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */

public class Pracownik extends Osoba
{
   float fWynagrodzenie; 
   
   Pracownik(String sImie, String sNazwisko, float fWynagrodzenie)
   {
       super(sImie,sNazwisko);
       this.fWynagrodzenie = fWynagrodzenie;  
   }

    @Override           
    void opis() 
    {
        System.out.println("Jestem Pracownikiem!!!!");
        System.out.println(this.sImie +" "+this.sNazwisko);
        System.out.println("Wynagrodzenie : " +this.fWynagrodzenie);
    }
}
