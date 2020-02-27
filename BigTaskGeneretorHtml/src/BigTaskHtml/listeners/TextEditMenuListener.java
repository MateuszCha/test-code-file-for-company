package BigTaskHtml.listeners;

import BigTaskHtml.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view){
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = null;
        if(e.getSource() instanceof JMenu)
            menu = (JMenu)e.getSource();
        for(Component itemComp: menu.getMenuComponents()){
            itemComp.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}