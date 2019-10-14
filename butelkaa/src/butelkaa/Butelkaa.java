/**
 *  
 * @author MCh
 * @version 1.0.1.2
 * 
 * This is <code>main</code> class
 */
package butelkaa;
/**
 * This is  <b>main</b> class 
 */
public class Butelkaa 
{

    public static void main(String[] args) 
    {
         Butla butelka[] = new Butla[50];
         for (int i = 0; i<butelka.length; i++) // for(Butla Obutelka: butelka) { Obutelka = new Butla(2.5F,6F);}
         {
             butelka[i] = new Butla(2.5F,6F);
         }
         System.out.println(butelka[32].toString()); 
          butelka[32].wlej(2);
         System.out.println(butelka[49].toString());
         butelka[49].wlej(6);
         System.out.println("----------------------------");
          System.out.println( butelka[0].toString()); 
         System.out.println(butelka[49].toString());
         butelka[49].przelej(7, butelka[1]);
         System.out.println( butelka[49].toString());   
         System.out.println( butelka[1].toString()); 
    }
    
}
