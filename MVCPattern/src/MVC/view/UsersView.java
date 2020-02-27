package MVC.view;

import MVC.bean.User;
import MVC.controller.Controller;
import MVC.model.DataModel;

public class UsersView implements View {
    private Controller controller;

    public void fireShowAllUsersEvent(){
        controller.onShowAllUsers();
    }
    public void fireShowDeletedUsersEvent() {
        controller.onShowAllDeletedUsers();
    }
    public void fireOpenUserEditFormEvent(long id) {
        this.controller.onOpenUserEditForm(id);
    }


    @Override
    public void refresh(DataModel dataModel) {
        if(dataModel.isDisplayDeletedUserList()) System.out.println("All deleted users:");
        else System.out.println("All users:");
        for(User listForOf : dataModel.getUsers())
            System.out.println("\t"+listForOf);
        System.out.println("===================================================");
    }
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
