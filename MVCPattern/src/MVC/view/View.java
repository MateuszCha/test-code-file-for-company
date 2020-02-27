package MVC.view;

import MVC.controller.Controller;
import MVC.model.DataModel;

public interface View {
    void refresh(DataModel dataModel);
    void setController(Controller controller);


}
