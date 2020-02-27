package BigTaskHtml;


import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view){
        this.view = view;
    }

    public void init(){
        createNewDocument();
    }
    public void exit(){
        System.exit(0);
    }
    public void resetDocument(){
        if(document != null)
            document.removeUndoableEditListener(view.getUndoListener());
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public static void main(String[] args){
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
            new HTMLEditorKit().read(reader, document, 0);
        } catch (Exception err) {
            ExceptionHandler.log(err);
        }
    }
    public String getPlainText() {
        StringWriter writer = new StringWriter();
        try {
            new HTMLEditorKit().write(writer, document, 0, document.getLength());
        } catch (Exception err) {
            ExceptionHandler.log(err);
        }
        return writer.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML New editor");
        view.resetUndo();
        currentFile = null;

    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int value = fileChooser.showOpenDialog(view);
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentFile = file;
            resetDocument();
            view.setTitle(file.getName());
            try {
                FileReader reader = new FileReader(currentFile);
                new HTMLEditorKit().read(reader,document,0);
                view.resetUndo();

            }catch (Exception err){
                ExceptionHandler.log(err);
            }
        }
    }

    public void saveDocument()
    {
        if(currentFile == null)
            saveDocumentAs();
        else {
            view.selectHtmlTab();
            view.setTitle(currentFile.getName());
            try {
                FileWriter writer = new FileWriter(currentFile);
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
                writer.close();
            }catch (Exception err){
                ExceptionHandler.log(err);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int value = fileChooser.showSaveDialog(view);
        //Be sure that the saveDocumentAs() method uses the JFileChooser.showSaveDialog() method to show the "Save File" file chooser dialog.
        if(value == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter writer = new FileWriter(currentFile);
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
                writer.close();
            }catch (Exception err){
                ExceptionHandler.log(err);
            }
        }
    }
}