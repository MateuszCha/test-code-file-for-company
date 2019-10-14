package dzidziczenie;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */

public abstract class Osoba 
{
    String sImie;
    String sNazwisko;  
    
    Osoba()
    {
     this.sImie = "brak";
     this.sNazwisko= "brak";
    }
    Osoba(String sImie, String sNazwisko)
    {
        this.sImie = sImie;
        this.sNazwisko = sNazwisko;    
       
    }
    abstract void opis();
}  

