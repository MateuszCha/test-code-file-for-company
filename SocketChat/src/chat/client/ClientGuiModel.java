package chat.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;



    public void addUser(String newUserName){
        this.allUserNames.add(newUserName);
    }
    public void deleteUser(String userName){
        if(userName == null) return;
        this.allUserNames.remove(userName);
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);
    }

    public String getNewMessage() {
        return newMessage;
    }
}
//2) Add a set of strings as a final allUserNames field. It will store a list of all the chat participants. Initialize it.
//3) Add a String newMessage field, which will store the new message received by the chat.client.
//4) Add an allUserNames getter that prevents modification of the returned Set. Figure out how this can be done using a method of the Collections class.
//5) Add a setter and a getter for the newMessage field.
//6) Add a void addUser(String newUserName) method that adds the participant's name to a set containing all the participants.
//7) Add a void deleteUser(String userName) method that removes the participant's name from the set.