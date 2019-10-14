package ziper;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import java.io.*;
import java.util.zip.*;



public class Ziper extends JFrame
{
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private ArrayList arreyList= new ArrayList();
    private JMenuBar  pasekTool = new JMenuBar();
    private JMenu plik = new JMenu("Plik");
    private Action cosdodawani = new Akcja("Dodaj", "ctrl D", "przycisk dodaje");
    private Action cosUsun = new Akcja("Usun", "ctrl U", "przycisk ktory usuwa");
    private Action cosZip  = new Akcja("ZIP", "ctrl Z", "pakuje do archiwum");
    private JButton przyciskDodaj = new JButton(cosdodawani);
    private JButton przyciskUsun = new JButton(cosUsun);
    private JButton przyciskZip = new JButton(cosZip);
    private JMenuItem itemMenuDodaj = new JMenuItem(cosdodawani);
    private JMenuItem itemMenuUsun = new JMenuItem(cosUsun);
    private JMenuItem itemMenuZip = new JMenuItem(cosZip);
    private DefaultListModel modelListy = new DefaultListModel(); 
    private JList lista = new JList(modelListy);
    private JFileChooser wyborPlikow = new JFileChooser();
    private JScrollPane scrol = new JScrollPane(lista);
    public static final int BUFFOR  = 1024;   
    
    public Ziper()
    {
        int iWys = Toolkit.getDefaultToolkit().getScreenSize().height;
        int iSzer = Toolkit.getDefaultToolkit().getScreenSize().width;   
        this.setMinimumSize(new Dimension(250, 200));
        this.setBounds(iSzer/2-150, iWys/2-150, 300 ,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponent();
    }
    
    private void initComponent()
    {
        lista.setBorder(BorderFactory.createEtchedBorder()); 
        GroupLayout grupa = new GroupLayout(this.getContentPane());
        grupa.setHorizontalGroup(
                grupa.createSequentialGroup()
                        .addComponent(scrol , 0, 100 , Short.MAX_VALUE )                        
                        .addGroup(grupa.createParallelGroup()
                                    .addComponent(przyciskDodaj,50, GroupLayout.DEFAULT_SIZE, 100)
                                    .addComponent(przyciskUsun, 50 ,GroupLayout.DEFAULT_SIZE, 100))                                    
                        .addContainerGap(0, Integer.MAX_VALUE)
                        .addGroup(grupa.createParallelGroup()
                                .addComponent(przyciskZip))
                               );
        grupa.setVerticalGroup(
                grupa.createParallelGroup()
                        .addComponent(scrol, 0, 100 , Short.MAX_VALUE)
                        .addGroup(grupa.createSequentialGroup()
                                 .addComponent(przyciskDodaj,10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                 .addComponent(przyciskUsun,10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                                 .addGap(0, 100, Short.MAX_VALUE)
                                 .addGroup(grupa.createSequentialGroup()
                                         .addContainerGap(0, Short.MAX_VALUE)
                                         .addComponent(przyciskZip))
                                                 
                              );
        grupa.setAutoCreateContainerGaps(true);
        grupa.setAutoCreateGaps(true);
        pasekTool.add(plik);
        plik.add(itemMenuDodaj);
        plik.add(itemMenuUsun);
        plik.add(itemMenuZip);                        
        this.setJMenuBar(pasekTool);
        this.getContentPane().setLayout(grupa);       
    }
    
    private class Akcja extends AbstractAction           
    {
        Akcja (String nazwa,String skrot,String opis)
        {            
            this.putValue(Action.NAME, nazwa);
            this.putValue(Action.SHORT_DESCRIPTION, opis);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(skrot));
            System.out.println(skrot);            
        }

        Akcja (String nazwa,String skrot,String opis,Icon ikona)
        {
            this(nazwa,skrot,opis);
            this.putValue(Action.SMALL_ICON, ikona);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().equals("Dodaj"))
                dodajArchiwum();
            if(e.getActionCommand().equals("Usun"))
                usunArchiwum(lista.getSelectedIndices());
            if(e.getActionCommand().equals("ZIP"))
                createZip();
        }
    };    
    
    private void dodajArchiwum()
    {
        int tmp;
        wyborPlikow.setCurrentDirectory(new File(System.getProperty("user.dir")));        
        wyborPlikow.setMultiSelectionEnabled(true);
        wyborPlikow.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);        
        tmp = wyborPlikow.showDialog(panel, "Dodaj");
        
        if (tmp == JFileChooser.APPROVE_OPTION) // == 0 
        {
            
            File  sciezki[] = wyborPlikow.getSelectedFiles(); 
            for(int i=0;i<sciezki.length;i++)
            {               
                if (isDuplicatedText(sciezki[i].getName()))
                {
                    modelListy.addElement(sciezki[i].getName());
                    arreyList.add(sciezki[i].getPath());
                    for(int j = 0;j<arreyList.size();j++)
                        System.out.println(arreyList.get(j));
                }                 
            }
        }
    }
     private void usunArchiwum(int index[])
     {
         System.out.println(index.length);
         for(int i = 0; i<index.length ;i++)
         {
            System.out.println(index[i]);
            modelListy.remove(index[i]-i);
            arreyList.remove(index[i]-i);
         }
     }
     private void createZip()
     {
        try 
        {
            int counter;
            byte tmpData[] = new byte[Ziper.BUFFOR];
            ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("nazwa.zip")));            
            for (int i = 0;i<modelListy.getSize();i++)
            {
                BufferedInputStream inS =new BufferedInputStream(new FileInputStream((String)arreyList.get(i)),Ziper.BUFFOR);
                zipOut.putNextEntry(new ZipEntry((String)modelListy.get(i)));

                while((counter = inS.read(tmpData, 0, BUFFOR))!= -1)
                {
                    zipOut.write(tmpData, 0, counter);
                }            
            }
            zipOut.closeEntry();
            zipOut.close();
         } 
        catch (FileNotFoundException ex) 
        {
             System.out.println(ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
         
     }
    
     private boolean isDuplicatedText(String nazwa)
     {                  
         for (int i=0;i<modelListy.getSize();i++)
         {
            if( nazwa.equals(modelListy.get(i)))                              
                return false;
         }
         return true;          
     }
     
    public static void main(String[] args) 
    {
       new Ziper().setVisible(true);
    }
  
}
