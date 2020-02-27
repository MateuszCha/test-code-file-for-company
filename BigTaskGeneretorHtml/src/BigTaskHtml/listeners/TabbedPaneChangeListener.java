package BigTaskHtml.listeners;

import BigTaskHtml.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPaneChangeListener implements ChangeListener { ///This class will listen for and handle changes to the state of the tabbed pane.
    private View view;

    public TabbedPaneChangeListener(View view){
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }
}
