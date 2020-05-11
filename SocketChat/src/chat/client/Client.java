package chat.client;

import chat.Connection;
import chat.ConsoleHelper;
import chat.Message;
import chat.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress()
    {
        String result = "localhost";
        ConsoleHelper.writeMessage("Set IP address server if works in same machine pleas write localhost");
        result = ConsoleHelper.readString();
        //localhost return
        return result;
    }
    protected int getServerPort(){
        ConsoleHelper.writeMessage("set PORT to will connect");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        ConsoleHelper.writeMessage("Set your name[login]");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    protected void sendTextMessage(String text)
    {
        try{
            Message message = new Message(MessageType.TEXT,text);
            connection.send(message);
        }catch (IOException err){
           //6) void sendTextMessage(String text) -
            // It creates a new text message, using the connection field to send
            // the passed text to the server. If an IOException occurs while the text is sent,
            // you need to display information about this user and assign false to the clientConnected
            // field.
            this.clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try
        {
            synchronized (this)
            {
                wait();
                if(clientConnected)
                    ConsoleHelper.writeMessage("chat.Connection established. To exit, enter 'exit'.");
                else ConsoleHelper.writeMessage("An error occurred while working with the chat.client.");
                while (clientConnected){
                    String s = ConsoleHelper.readString();
                    if (s.equals("exit"))
                        break; //or break;
                    if (shouldSendTextFromConsole()){
                        try{
                            connection.send(new Message(MessageType.TEXT, s));
                        }catch (IOException err){
                            err.printStackTrace();
            }   }   }   }
        } catch (InterruptedException err){
            notify();
        }
    }

///////////////////////////////////CLASS INNER//////////////////////////////////
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " has joined the chat");
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + "userName has left the chat");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while(!isInterrupted()){
                Message message = connection.receive();
                Message newMessage = null;
                String date = null;
                if(message.getType() == MessageType.NAME_REQUEST)
                {
                    date = getUserName();
                    newMessage = new Message(MessageType.USER_NAME,date);
                    connection.send(newMessage);
                    continue;
                }
                if(message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                }
                throw new IOException("Unexpected chat.MessageType");
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while(!isInterrupted())
            {
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                    continue;
                }
                if(message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                    continue;
                }
                if(message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                    continue;
                }
                throw new IOException("Unexpected chat.MessageType");
            }
        }
        public void run(){
            String serverAddress = getServerAddress();
            int port = getServerPort();
            System.out.println(serverAddress + " " + port);
            try {
                Socket socket = new Socket(serverAddress, port);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch (IOException | ClassNotFoundException err){
                notifyConnectionStatusChanged(false);
            }
        }
}


///////////////////////////////////MAIN /////////////////////////////////////
    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }
}
