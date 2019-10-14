package rysowanie;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.*;


public class Rysowanie extends JFrame
{
    private PanelAnimacji panel = new PanelAnimacji ();
    private PanelRysowania rysunek =new PanelRysowania();
    Thread watek;
    private ThreadGroup grupa =new ThreadGroup("GrupaKropelke");
    private ArrayList lista = new ArrayList();    
    public static int i = 0;  
    
    public Rysowanie()
    {
       int wys = Toolkit.getDefaultToolkit().getScreenSize().width;
       int sze = Toolkit.getDefaultToolkit().getScreenSize().height;       
       JButton start = (JButton)rysunek.add(new JButton("Start"));
       JButton stop = (JButton)rysunek.add(new JButton("Stop"));
       JButton usun = (JButton)rysunek.add(new JButton("Usun"));       
      
       usun.addActionListener(new ActionListener() 
       {
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               usunAction();
               stopAnimation();
           }
       });
       start.addActionListener(new ActionListener() 
       {
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               startAnimacji();
           }
       });
       stop.addActionListener(new ActionListener() 
       {          
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               stopAnimation();
           }
       });        
       panel.setBackground(Color.red);
       this.getContentPane().add(panel);
       this.getContentPane().add(rysunek, BorderLayout.SOUTH);
       this.setBounds(wys/2-150, sze/2-150, 300, 300);
       this.setTitle("nazwa");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
     
   public void startAnimacji()
   {
       panel.addKropelka();
   }
   public void stopAnimation()
   {
       panel.stop();
   }
   public void usunAction()
   {
       panel.usun();
   }
   public static void main(String[] args) 
   {
       new Rysowanie().setVisible(true);
   }
  
    
    
    class PanelAnimacji extends JPanel
    {
        //Thread watek2;
        public void addKropelka()
        {
            
             lista.add(new Kropelka());
             watek = new Thread(grupa, new KropelkaRunnable((Kropelka)lista.get(lista.size()-1)));              
             watek.start();  
        }       
        public void stop() 
        {  
             grupa.interrupt();             
        }
        public void usun()
        {
         lista.clear();
         repaint();
        }
        public void paintComponent(Graphics g)
        {
           super.paintComponent(g);
           for(int i =0; i<lista.size();i++)
           {
               g.drawImage(Kropelka.getImage(), ((Kropelka)lista.get(i)).x, ((Kropelka)lista.get(i)).y, null);
           }
        }       
       
        public class KropelkaRunnable implements Runnable
        {           
            Kropelka kropleka;

            public KropelkaRunnable(Kropelka k)
            {
                this.kropleka = k;
            }
            
            @Override
            public void run() 
            { 
                try 
                {
                    while(true)
                    {
                       while(Thread.currentThread().isInterrupted())
                       {
                           grupa.wait();                               
                       }
                       this.kropleka.ruszKropelka(panel);
                       repaint();               
                       Thread.sleep(2);
                    }
                }                
                catch(InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                    repaint();
                }
            }
        }
    }    
    class PanelRysowania extends JPanel
    {
    
        public PanelRysowania()
        {
           super();
        }
    }
}

class Kropelka
{
    int x = 0;
    int y = 0;
    int dx = 1;
    int dy = 1;
    int yKropelki = kropelka.getHeight(null);
    int xKropelki = kropelka.getWidth(null);
    public static Image kropelka = new ImageIcon("kropelka.gif").getImage();
   
    public void ruszKropelka(JPanel pojemnik)
    {
        Rectangle granice = pojemnik.getBounds(); // pobranie wielkosci pojemnika czyli panelu        
        this.x += dx;
        this.y += dy;
        
        if (y +yKropelki >= granice.getMaxY())
        {
            this.dy = -1;    
        }
        if (y <= granice.getMinY())
        {
            this.dy = 1;    
        }
        if (x +xKropelki >= granice.getMaxX())
        {
            this.dx = -1;    
        }
        if (x <= granice.getMinX())
        {
            this.dx = 1;    
        }
    }    
    public static Image getImage()
    {
        return Kropelka.kropelka;
    }
}