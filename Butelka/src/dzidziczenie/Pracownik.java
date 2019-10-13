package dzidziczenie;


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
