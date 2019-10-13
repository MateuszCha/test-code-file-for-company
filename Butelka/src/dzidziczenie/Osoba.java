package dzidziczenie;


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

