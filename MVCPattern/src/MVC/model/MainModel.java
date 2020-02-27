package MVC.model;

import MVC.bean.User;
import MVC.model.service.UserService;
import MVC.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private UserService userService = new UserServiceImpl();
    private DataModel dataModel = new DataModel();

    @Override
    public DataModel getDataModel() {
        return this.dataModel;
    }
    public void loadDeletedUsers() {
        dataModel.setDisplayDeletedUserList(true);
        List users = userService.getAllDeletedUsers();
        dataModel.setUsers(users);
    }
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        dataModel.setActiveUser(user);
    }
    public void deleteUserById(long id){
        //List users = getAllUsers();
        userService.deleteUser(id);
        this.dataModel.setUsers(getAllUsers());
    }
    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name,id,level);
        this.dataModel.setUsers(getAllUsers());

    }
    private List<User> getAllUsers(){
        this.dataModel.setUsers(userService.getUsersBetweenLevels(1,100));
        return (userService.filterOnlyActiveUsers(dataModel.getUsers()));
    }


    @Override
    public void loadUsers() {
        /*
        You have not used the setter to save the list of users to the dataModel object in the MainModel class's loadDeletedUsers method.
        3. Implement the loadUsers method:
3.1. Get all users between levels 1 and 100. (Use the getUsersBetweenLevels(1, 100) method.)
3.2. Put all of the users into dataModel.
         */
        dataModel.setDisplayDeletedUserList(false);
        this.dataModel.setUsers(userService.filterOnlyActiveUsers(dataModel.getUsers()));
        dataModel.setUsers(userService.getUsersBetweenLevels(1,100));

    }
}
