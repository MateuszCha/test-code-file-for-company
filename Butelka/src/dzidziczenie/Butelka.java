package dzidziczenie;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */

public class Butelka
{
    public static void main(String[] args)
    {
        Osoba osoba[]=new Osoba[5];
        osoba[0] = new Pracownik("Mateusz", "Charyszny", 1.5F);
        osoba[1] = new Pracownik("Jan", "nowak", 2.5F);
        osoba[2] = new Student("Ania", "Tys", 0);
        osoba[4] = new Student("Dominik", "Ptys", 20);
        
        osoba[2].opis();
         System.out.println("------------------");
        for (int i=0; i<osoba.length;i++)          
        {
            if (osoba[i] instanceof Osoba ) osoba[i].opis();            
            System.out.println("------------------"+i);            
        }
        /*
        for(Osoba osobaa: osoba)
        {
            if(osobaa instanceof Osoba) osobaa.opis();
            System.out.println("------------------");
        }
        */
    }    
}
