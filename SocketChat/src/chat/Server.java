package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<String, Connection>();

    private static class Handler extends Thread {
        private Socket socket;
        Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String newUserName = null;
            while(!isInterrupted())
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message messageResponse = connection.receive();
                if (messageResponse.getType() == MessageType.USER_NAME) {
                    newUserName = messageResponse.getData();
                    if (newUserName != null && !newUserName.isEmpty() && !connectionMap.containsKey(newUserName)) {
                        connectionMap.put(newUserName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return newUserName ;
        }
        private void notifyUsers(Connection connection, String userName) throws IOException
        {
            for(Map.Entry<String, Connection> pairs: connectionMap.entrySet()){
                if(pairs.getKey().equals(userName))
                    continue;
                Message message = new Message(MessageType.USER_ADDED,pairs.getKey());
                connection.send(message);
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while(!isInterrupted())
            {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String newTextMessage = userName + ": " + message.getData();
                    Message newMessage = new Message(MessageType.TEXT, newTextMessage);
                    sendBroadcastMessage(newMessage);
                } else {
                    ConsoleHelper.writeMessage("ERROR: Date not a \"TEXT\".");
                }
            }
        }
        @Override
        public void run()
        {
            ConsoleHelper.writeMessage("A connection has been established with " +socket.getRemoteSocketAddress());
            String userName = null;
            try(Connection connection = new Connection(socket))
            {
                userName = serverHandshake(connection);
                Message message = new Message(MessageType.USER_ADDED,userName);
                sendBroadcastMessage(message);
                notifyUsers(connection,userName);
                serverMainLoop(connection,userName);
            }catch (IOException | ClassNotFoundException err){
                ConsoleHelper.writeMessage("An error occurred while communicating with the remote address.");
            }
            finally
            {
                ConsoleHelper.writeMessage("chat.Connection with " + socket.getRemoteSocketAddress() + " closed.");
                if(userName != null) {
                    Message messageRemove = new Message(MessageType.USER_REMOVED,userName);
                    connectionMap.remove(userName);
                    sendBroadcastMessage(messageRemove);
                }
            }
        }
    }
    public static void sendBroadcastMessage(Message message){
        try{
            for(Map.Entry<String, Connection> pairs: connectionMap.entrySet()){
                pairs.getValue().send(message);
            }
        }catch (IOException err){
            ConsoleHelper.writeMessage("chat.Message couldn't be sent.");
        }

    }

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("chat.Server is running");
            while(true){
               Socket socket =  serverSocket.accept();
               new Handler(socket).start();
            }
        }catch (IOException err){
            try {
                serverSocket.close();
            }catch (IOException e){}
            err.printStackTrace();
        }

    }
}
///So let's recap:
//•	You wrote a text messaging server.
//•	You wrote a console-based chat.client that can connect to the server and exchange messages with other chat participants.
//•	You wrote a bot chat.client that can receive requests and send information about the current date and time.
//•	You wrote a chat chat.client with a graphical interface.
//
//Here are some things you might add or improve:
//•	You could add support for private messages (when a message is sent to a specific participant instead of everyone).
//•	You could extend the bot's capabilities. Try to teach it to respond to super simple questions or to make jokes from time to time.
//•	Add the ability to transfer files between users.
//•	Add a context menu to the GUI-based chat.client, for example, to send a private message to someone from a list of participants.
//•	Color the messages in the GUI-based chat.client, depending on the sender.
//•	Add the ability for the server to block participants, for example, for offensive language in messages.
//•	Add another million features and abilities!
//
//You learned how to:
//•	Work with sockets.
//•	Use serialization and deserialization.
//•	Create and synchronize multi-threaded applications, use the volatile modifier, and use classes from the java.util.concurrent library.
//•	Apply the MVC pattern.
//•	Use inner and nested classes.
//•	Work with the Swing library.
//•	Use the Calendar and SimpleDateFormat classes.
//
//Keep it up!