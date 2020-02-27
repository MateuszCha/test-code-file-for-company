package MVC.model;

public interface Model {
    public abstract DataModel getDataModel();
    public abstract void loadUsers();
    public void loadDeletedUsers();
    void loadUserById(long userId);
    public void deleteUserById(long id);
    public void changeUserData(String name, long id, int level);
}
