package BigTaskHtml;

import BigTaskHtml.listeners.FrameListener;
import BigTaskHtml.listeners.TabbedPaneChangeListener;
import BigTaskHtml.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();// - This will be a pane with two tabs.
    private JTextPane htmlTextPane = new JTextPane();// This will be a component for editing the HTML visually.
    private JEditorPane plainTextPane = new JEditorPane();//This will be a component for editing HTML as text. It will display the HTML code (HTML tags and their contents).
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }

    }

    public void init(){ //They will be responsible for initializing the controller and view.
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }
    public void exit(){
        controller.exit();
    }
    public void initMenuBar(){ //They will be responsible for initializing the menus and editor panes.
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this,menuBar);
        MenuHelper.initEditMenu(this,menuBar);
        MenuHelper.initStyleMenu(this,menuBar);
        MenuHelper.initAlignMenu(this,menuBar);
        MenuHelper.initColorMenu(this,menuBar);
        MenuHelper.initFontMenu(this,menuBar);
        MenuHelper.initHelpMenu(this,menuBar);
        getContentPane().add(menuBar,BorderLayout.NORTH);
    }
    public void initEditor(){//They will be responsible for initializing the menus and editor panes.
            this.htmlTextPane.setContentType("text/html");
            JScrollPane scrollPaneHTML = new JScrollPane(this.htmlTextPane);
            this.tabbedPane.addTab("HTML",scrollPaneHTML);
            JScrollPane scrollPaneTEXT = new JScrollPane(this.plainTextPane);
            this.tabbedPane.addTab("Text",scrollPaneTEXT);
            this.tabbedPane.setPreferredSize(new Dimension(200,200));
            this.tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
            getContentPane().add(tabbedPane,BorderLayout.CENTER);
    }
    public void initGui(){  //  It will initialize the graphical interface
        initMenuBar();
        initEditor();
        pack();
    }
    public void selectedTabChanged() {
        switch (tabbedPane.getSelectedIndex())
        {
            case 0:
                controller.setPlainText(plainTextPane.getText());
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText());
                break;

        }
        resetUndo();
    }
    public boolean canUndo() {
        return undoManager.canUndo();
    }
    public boolean canRedo(){
        return undoManager.canRedo();
    }
    public void undo(){
        try{
            undoManager.undo();
        }catch (Exception err){
            ExceptionHandler.log(err);
        }

    }
    public void redo(){
        try{
            undoManager.redo();
        }catch (Exception err){
            ExceptionHandler.log(err);
        }
    }

    public Controller getController() {
        return controller;
    }
    public UndoListener getUndoListener() {
        return undoListener;
    }
    public void resetUndo(){
        undoManager.discardAllEdits();
    }
    public boolean isHtmlTabSelected(){
        return (tabbedPane.getSelectedIndex() == 0);
    }
    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }
    public void showAbout(){
        JOptionPane.showMessageDialog(this,
                "Program zrobiony w paincie w sumie niwiem co napisac \n chdoz wiem ze i tak nikt teogo nie oprzeczyta \n moge pisacac co chce i gdzie checxD",
                "showAbout",
                JOptionPane.INFORMATION_MESSAGE
                );
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setUndoListener(UndoListener undoListener) {
        this.undoListener = undoListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        //19.1. Get a command from the event using the getActionCommand() method. It will be an ordinary string. You can use this string to understand which menu item created this event.
        switch (str){
            case "New":
                controller.createNewDocument();
                break;
            case "Open":
                controller.openDocument();
                break;
            case "Save":
                controller.saveDocument();
                break;
            case "Save as...":
                controller.saveDocumentAs();
                break;
            case "Exit":
                controller.exit();
                break;
            case "About":
                showAbout();
                break;
        }
    }
}


