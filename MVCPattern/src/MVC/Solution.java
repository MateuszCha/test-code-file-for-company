package MVC;

import MVC.controller.Controller;
import MVC.model.MainModel;
import MVC.model.Model;
import MVC.view.EditUserView;
import MVC.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);

        controller.setEditUserView(editUserView);
        usersView.fireShowAllUsersEvent();
        usersView.fireOpenUserEditFormEvent(126L);
        editUserView.fireUserDataChangedEvent("test",125,32);
        //usersView.fireShowAllUsersEvent();
        editUserView.fireUserDeletedEvent(124L);
        usersView.fireShowDeletedUsersEvent();
    }

}