package butelkaa;

public class Butelkaa {

    public static void main(String[] args) 
    {
         butla butelka[] = new butla[50];
         for (int i = 0; i<butelka.length; i++)
         {
             butelka[i] = new butla(2.5F,6F);
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
