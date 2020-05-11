package chat.client;



public class ClientGuiController extends Client {
    private chat.client.ClientGuiModel model = new chat.client.ClientGuiModel();
    private chat.client.ClientGuiView view = new chat.client.ClientGuiView(this);

    public ClientGuiModel getModel(){
        return this.model;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    @Override
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.run();
    }

    @Override
    protected String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort() {
        return view.getServerPort();
    }

    @Override
    protected String getUserName() {
        return view.getUserName();
    }

    /////////////////////////////CLASS INNER///////////////////////////
    public class GuiSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }
////////////////////////////////////////MAIN////////////////////////////
    public static void main(String[] args){
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }
}
