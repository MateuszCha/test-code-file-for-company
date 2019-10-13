package butelkaa;


public class butla 
{
   private float fIloscLitrow;
   private float fPojemnosc;
   private static int idCalosc =0 ;
   private int id = 0;
    
   butla()
   {
       this(0,0);
   }
   butla(float iloscLitrow)       
   {
       this(iloscLitrow, 0);
   }
   butla(float iloscLitrow, float fPojemnosc)
   {
       
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
        this.fIloscLitrow = ilosc;        
    }
    float getIlosc()
    {
        return this.fIloscLitrow;
    }
    private void setPojemnosc(float fPojemnosc)
    {
        this.fPojemnosc = fPojemnosc;
    }
    float getPojemnosc()
    {
        return this.fPojemnosc;
    }
    int getId()
    {
        return this.id;
    }
    
    float wlej(float fIloscLitrow)
    {
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
    void przelej(float fIloscLitrow, butla butelka ) // boolen
    {        
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
      return "butelak nr "+this.getId()+" o ilosci plynu : "+this.getIlosc()+" oraz pojemnosci : "+this.getPojemnosc();   
    }
}
