package butelkaa;
/**
 * 
 *  This class
 */ 
 
public class Butla 
{
    
 /**
 *  @param fIloscLitrow This param represetns how much water is in a bottel  
 *  @param fPojemnosc This param represetns size of bottel 
 *  @param idCalosc This param is static and icrements when a new object is created
 *  @param id This parmm is ID of this object 
 * 
 */
    
   private float fIloscLitrow;
   private float fPojemnosc;
   private static int idCalosc =0 ;
   private int id = 0;

   
    
  Butla()
   {
       /**
        * This is default constrator.
        * Who calls to new one with parametrs (0,0)
        * 
        */
       this(0,0);
   }
   Butla(float iloscLitrow)       
   {
       /**
        * This is constrator with one param.
        * And it set size water im the bottel
        * Who calls to new one with parametrs (parm,0)        * 
        */
       this(iloscLitrow, 0);
   }
   Butla(float iloscLitrow, float fPojemnosc)
   {
       /**
        * This is constrator with two param.
        * First param  set size water in the bottle   
        * Secend param set size of bottle 
        */
       
       setPojemnosc(fPojemnosc); //this.fPojemnosc = fPojemnosc; 
       setIlosc((fPojemnosc > iloscLitrow) ?iloscLitrow:fPojemnosc); //this.fPojemnosc = (fPojemnosc > iloscLitrow) ?iloscLitrow:fPojemnosc);
      /* if (fPojemnosc > iloscLitrow )
            this.fIloscLitrow = iloscLitrow;
       else 
           this.fIloscLitrow = fPojemnosc;
       */
       idCalosc++; 
       this.id = idCalosc;
   }   
   private void setIlosc(float ilosc)
    {
    /**
     * This is set method.
     * Parma set size water im the bottle
     */
        this.fIloscLitrow = ilosc;        
    }
    float getIlosc()
    {
    /**
     * This is get method.
     * @return <b>float</b> Return size water im the bottle
     */   
        return this.fIloscLitrow;
    }
    private void setPojemnosc(float fPojemnosc)
    {
     /**
     * This is set method.
     * Parma set size of bottle
     */
        this.fPojemnosc = fPojemnosc;
    }
    float getPojemnosc()
    {
     /**
     * This is get method.
     * @return <b>float</b> Return size of bottel
     */   
        return this.fPojemnosc;
    }    
    int getId()
    {
     /**
     * This is get method.
     * @return <b>int</b> Return ID of this object     
     */   
        return this.id;
    }
    
    float wlej(float fIloscLitrow)
    {
    /**
     * This method add value of water to the size watter in the bottel.
     * And check how many water do you may add in bottle to make it full. Defined by a class parma which is called "this.fPojemnosc"  
     * @Parma fIloscLitrow  This parma represents how many water do you want add into the bottel.     * 
     * @return <b>float</b> Return the value of water over limit or "0".  
     * 
     */
      float fKontrolna = 0F;       
        if (this.getPojemnosc() >= this.getIlosc()+fIloscLitrow)
        {
            this.setIlosc(this.getIlosc()+fIloscLitrow);
            System.out.println("ilosc: wlewana: "+fIloscLitrow +" ilosc w butelce nr "
                    +this.getId()+" przed wlaniem :"+(this.getIlosc()-fIloscLitrow) +" oraz po wlaniu : "+this.getIlosc() );            
            return 0;
        }
        else
        {
            fKontrolna = this.getIlosc()+fIloscLitrow-this.getPojemnosc();            
            this.setIlosc(this.getPojemnosc());          
            return fKontrolna;
        } 
    }
    
    float wylej(float fIloscLitrow)
    {
      /**
     * This method remove value of water from the size watter in the bottel.
     * And check how many water do you may remove from the bottle to make it empty. Defined by a class parma which is called "this.fIloscLitrow"  
     * @Parma fIloscLitrow  This parma represents how many water do you want remove from the bottel.     * 
     * @return <b>float</b> Return the value of water under limit or "0". Limit define by water size in bottel
     * <pre>
     * Example:
     *  if the water size is bigger than "0" then return value is "0"
     *  else 
     * return value under limit
     * </pre>
     */
        float fKontrolna = 0F;
        if (this.fIloscLitrow-fIloscLitrow >= 0)
        {
            this.setIlosc(this.fIloscLitrow-fIloscLitrow);
            return 0;
        }
        else 
        {
            fKontrolna = fIloscLitrow-this.fIloscLitrow; 
            this.setIlosc(0);
            return fKontrolna;
        }
        
    }
    void przelej(float fIloscLitrow, Butla butelka ) // boolen
    {        
         /**
     * This method transfer waterm between two bottels.
     * @Parma fIloscLitrow  This parma represents how many water do you transfer betwenn the bottel.  
     * @Parma butelka <b> Object: Butla </b> This object represent bottle to which transfer water.
     * 
     */
        float fKontrolna = 0F;       
        if(fIloscLitrow > this.getIlosc())
        {
            System.out.println("Za duzoa wody do wylania o " +(fIloscLitrow-this.fIloscLitrow));
            fKontrolna = butelka.wlej(this.fIloscLitrow);
            fKontrolna += this.wylej(this.getIlosc());
            this.setIlosc(fKontrolna);           
        }else 
        {
            System.out.println("wylano z butelki nr "+this.getId() +" litrow: "+fIloscLitrow);
            fKontrolna = this.wylej(fIloscLitrow);
            fKontrolna += butelka.wlej(fIloscLitrow);
            System.out.println("butelka nr: "+ butelka.getId() + " o pojemnosci: "+ butelka.getPojemnosc() +" napelniono do "
            +butelka.getIlosc());
            System.out.println("po napelnieniu butelki nr: "+butelka.getId() +" zostalo: "+fKontrolna 
                      +" litrow wody ktore przelano do butelki: "+this.getId() +" w butelce przelewajacej jest "+this.getIlosc()+" litrow wody." );
            this.setIlosc(this.getIlosc()+fKontrolna);    
        } 
    }
    
    public String toString()
    {
      /** 
       * this is ovverid method toString
       * And describes the class parm. 
       */
      return "butelak nr "+this.getId()+" o ilosci plynu : "+this.getIlosc()+" oraz pojemnosci : "+this.getPojemnosc();   
    }
}
