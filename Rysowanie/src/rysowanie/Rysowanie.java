package rysowanie;

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
    
    public Rysowanie()
    {
       int wys = Toolkit.getDefaultToolkit().getScreenSize().width;
       int sze = Toolkit.getDefaultToolkit().getScreenSize().height;
       
       JButton start = (JButton)rysunek.add(new JButton("Start")); // przypisanie dcomponentu do butona ciekawe
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
    
    
    public static void main(String[] args) 
    {
       new Rysowanie().setVisible(true);
    }
    

    
  
  class PanelRysowania extends JPanel
  {
    
    public PanelRysowania()
    {
       super();
    }
       
//       this.add(new JButton("next"){          // stworzenie klasy anonimowej jbutton i nadpiasnie metody
//       public void paintComponent(Graphics g) // metoda sluzoca do rysowanioa komponentow 
//                                              // Jpanel roszerzane jest przez Jcomponent do ktorego nalzey ta metoda roszeza 
//       {
//           super.paintComponent(g);          
//       }
//           
//       });
    
//    public void paintComponent(Graphics g) // nadpisanie metody grafiki
//    {
//           super.paintComponent(g);
//           //g.drawString("napis", 0, 40); // pisze napis na wspozednych (0,40)
//           //g.drawImage(new ImageIcon("kropelka.gif").getImage(), 0 , 100, this); // ryswoanie obrazka na bordergrund
//           //g.drawLine(0, 100, 10, 150);
//           //g.drawArc(100, 100, 400, 400, 100, 300);
//           for(int i =0; i<lista.size();i++)
//           {
//               g.drawImage(Kropelka.getImage(), ((Kropelka)lista.get(i)).x, ((Kropelka)lista.get(i)).y, null);
//           }
//    
//    }
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

    
    
    
    class PanelAnimacji extends JPanel
    {
        Thread watek2;
        public void addKropelka()
        {
             lista.add(new Kropelka());
             watek = new Thread(grupa, new KropelkaRunnable((Kropelka)lista.get(lista.size()-1)));
             watek2 = new Thread();
             watek2.start();
             watek.start(); // odpala metode run w klasie KropelakRunnable
        
             
             grupa.list(); // pokazuje wszsytkie watki
        }
        public void stop() // zatrzymuje dana animacie czyli watek
        {            
          grupa.interrupt(); // przerywa watek tworzy wyjatek // tworzy wyjatek do grupy watkow
          
              try 
              {
                while(Thread.currentThread().isInterrupted())
                {
                watek2.wait();
                }
                watek2.notifyAll();
              } catch (InterruptedException ex) 
              {
                  System.out.println(ex.getMessage());
              }
              finally
              {
            
              }
              
          
        }
        public void usun()
        {
         lista.clear();
         repaint();
        }
        public void paintComponent(Graphics g) // nadpisanie metody grafiki
    {
           super.paintComponent(g);
           //g.drawString("napis", 0, 40); // pisze napis na wspozednych (0,40)
           //g.drawImage(new ImageIcon("kropelka.gif").getImage(), 0 , 100, this); // ryswoanie obrazka na bordergrund
           //g.drawLine(0, 100, 10, 150);
           //g.drawArc(100, 100, 400, 400, 100, 300);
           for(int i =0; i<lista.size();i++)
           {
               g.drawImage(Kropelka.getImage(), ((Kropelka)lista.get(i)).x, ((Kropelka)lista.get(i)).y, null);
           }
    
    }        
    
   
    public class KropelkaRunnable implements Runnable
    {
        Thread watek = new Thread();
        Kropelka kropleka;
        
        public KropelkaRunnable(Kropelka k)
        {
            
            this.kropleka = k;
        }
        @Override
        public void run() 
        { 
            try //// zatrzmywaywabue watkow
            {
                while(true)
                {
                   while(Thread.currentThread().isInterrupted())
                   {
                       grupa.wait();                
                        //this.paint(this.getGraphics());                
                   }
                   this.kropleka.ruszKropelka(panel);
                   repaint(); // metoda odryswouje wszsytkiwe kompomnentuy                
                   Thread.sleep(2);
                  // grupa.notifyAll();
                }
            }
            catch(InterruptedException ex)
            {
             System.out.println(ex.getMessage());
             //lista.clear();
             repaint(); // odsiweza szybke 
            }
                    
                
            }
        }
    }
        
    private ThreadGroup grupa =new ThreadGroup("GrupaKropelke");
    private ArrayList lista = new ArrayList();    
    public static int i = 0;  
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