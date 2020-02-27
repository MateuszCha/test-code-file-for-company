package MVC.view;

import MVC.bean.User;
import MVC.controller.Controller;
import MVC.model.DataModel;

public class EditUserView implements View{
    private Controller controller;

    public void fireUserDeletedEvent(long id){
        this.controller.onDeleteUser(id);
    }
    public void fireUserDataChangedEvent(String name, long id, int level){
        this.controller.onChangeUserData(name,id,level);

    }

    @Override
    public void refresh(DataModel dataModel) {
        User user = dataModel.getActiveUser();
        System.out.println("User to be edited:");
        System.out.println("\t"+ user);
        System.out.println("===================================================");
    }
    @Override
    public void setController(Controller controller) {
        this.controller = controller;

    }
}
