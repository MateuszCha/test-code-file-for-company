package chat;

public enum MessageType {
    NAME_REQUEST,
    USER_NAME,
    NAME_ACCEPTED,
    TEXT,
    USER_ADDED,
    USER_REMOVED
}
//Let's outline the main aspects of the protocol:
//- When a new chat.client wants to connect to the server, the server should request the chat.client name.
//- When a chat.client receives a name request from the server, it should send its name to the server.
//- When the server receives a chat.client name, it should accept the name or request a new one.
//- When a new chat.client is added to the chat, the server should inform the other participants about the new chat.client.
//- When a chat.client leaves the chat, the server should inform the other participants.
//- When the server receives a text message from a chat.client, it should forward it to all the other chat participants.
//
//For each item in the protocol described above, add corresponding values to the chat.MessageType enum:
//1) NAME_REQUEST – A name request.
//2) USER_NAME – A username.
//3) NAME_ACCEPTED – The name is accepted.
//4) TEXT – A text message.
//5) USER_ADDED – The user was added.
//6) USER_REMOVED – The user was deleted.