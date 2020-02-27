package MVC.controller;

import MVC.model.Model;
import MVC.view.EditUserView;
import MVC.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void onShowAllUsers(){
        this.model.loadUsers();
        this.usersView.refresh(model.getDataModel());
    }

    public void onShowAllDeletedUsers() {
        this.model.loadDeletedUsers();
        this.usersView.refresh(model.getDataModel());
    }
    public void onOpenUserEditForm(long userId) {
        this.model.loadUserById(userId);
        this.editUserView.refresh(model.getDataModel());
    }
    public void onDeleteUser(long id){
        this.model.deleteUserById(id);
        this.usersView.refresh(model.getDataModel());
    }
    public void onChangeUserData(String name, long id, int level) {
        this.model.changeUserData(name,id,level);
        this.model.loadUserById(id);
        this.editUserView.refresh(model.getDataModel());
        this.usersView.refresh(model.getDataModel());
        //You did not update the UsersView object in the onChangeUserData(String, long, int) method.
        // You need to use MVC.model.getDataModel() as the argument for the refresh method.
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }
    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }
    public EditUserView getEditUserView() {
        return editUserView;
    }
}
