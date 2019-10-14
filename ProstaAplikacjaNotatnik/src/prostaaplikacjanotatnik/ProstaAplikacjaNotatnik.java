package prostaaplikacjanotatnik;
/**
 * 
 * @see <a href="https://github.com/MateuszCha/test-code-file-for-company/blob/master/butelkaa/src/butelkaa/Butla.java" target="_blanck">My example documentation finde there</a>
 * @author Media
 */

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;


public class ProstaAplikacjaNotatnik extends JFrame
{

   
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JButton przycisk = new JButton("Zapisz");
    JMenu plik;
    JMenu opcje;
    JMenu pomoc;
    JScrollPane przewijanieTeksut;
    JCheckBoxMenuItem tylkoDoOdczytu;
    JMenuItem zapisz;
    JTextArea textArea;
    JMenuBar edycja;
    int a;
    
    
    public ProstaAplikacjaNotatnik()
    {
          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        int szer = Toolkit.getDefaultToolkit().getScreenSize().height;
        int wys = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.setBounds(wys/2-150, szer/2-150, 300, 300);  
        this.setTitle("OknoDoPisania");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("obraze.jpg"));
        initComponents();
    }
    private void initComponents()
    {        
        textArea = new JTextArea(3, 1);  
        edycja = new JMenuBar();  
        plik = edycja.add(new JMenu("Plik"));
        opcje = new JMenu("opcje");
        pomoc = new JMenu("Pomoc");
        zapisz = new JMenuItem("zapisz");
        przewijanieTeksut = new JScrollPane(textArea);
        tylkoDoOdczytu = new JCheckBoxMenuItem("Tylko do odczytu");
        przycisk = new JButton("Zapisz");
        JSpinner spiener =new JSpinner();
        SpinnerListModel listaSpiner = new SpinnerListModel();
        spiener.setModel(listaSpiner);
        SpinnerNumberModel nuber = new SpinnerNumberModel();
         
        pomoc.add(new JMenuItem("FAQ"));
        plik.add(new JMenuItem("nowy"));
        plik.addSeparator();
        plik.add(zapisz);
        plik.add(new JMenuItem("wczytaj"));
        plik.addSeparator();
        plik.add(opcje);
        opcje.add(new JMenuItem("opcje 1"));
        opcje.add(new JMenuItem("opcje 2"));
        opcje.add(tylkoDoOdczytu);
        edycja.add(pomoc);
        zapisz.setMnemonic('z');                            
        zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl  S")); 
        zapisz.setToolTipText("zapisaie nie dysku pliku"); 
        przycisk.setToolTipText("zapisanie na dysku pliku");
        
        
        przycisk.addActionListener((ActionEvent ae) -> { // lambda this is shortcut:      
            przyciskKlik(ae);
        });
        /* .addActionListener(new Action Listener ()
          @Override
            public void actionPerformed(ActionEvent ae) 
            { 
                przyciskKlik(ae);
            }
        
        */
        tylkoDoOdczytu.addActionListener((ActionEvent ae) -> { 
                TylkoDoOdczytu();
        });                 
        textArea.addKeyListener(new KeyAdapter() 
        {
        public void keyTyped(KeyEvent ke)
        {
             if (((JTextComponent)textArea).getSelectionEnd() != a)
           {
               if (tylkoDoOdczytu.isSelected() == false)
               {
                przycisk.setEnabled(true);
                zapisz.setEnabled(true);
                }
           }
        }  
        });        
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            { 
                przyciskKlik(ae);
            }
            
        });
                
        
        
        this.setJMenuBar(edycja);        
        panel.add(przycisk);   
        this.getContentPane().setLayout(new GridLayout(2, 1));
        this.getContentPane().add(przewijanieTeksut);
        this.getContentPane().add(przycisk);
       
    }
    
    private void TylkoDoOdczytu()
    {
         if (true==tylkoDoOdczytu.isSelected())
         { 
             System.out.println("cos");
             przycisk.setEnabled(false);
             zapisz.setEnabled(false);
             textArea.setEditable(false);
         }else 
         {
             przycisk.setEnabled(true);
             zapisz.setEnabled(true);
             textArea.setEditable(true);
         }
         
    }
    private void przyciskKlik(ActionEvent ae)
    {
            przycisk.setEnabled(false);
            zapisz.setEnabled(false);
            a = ((JTextComponent)textArea).getSelectionEnd();
            System.out.println("znaki tekst "+a);
            System.out.println("Zapisuje na dysku");
            
    }
    
    public static void main(String[] args) 
    {
        new ProstaAplikacjaNotatnik().setVisible(true);
    }
}


