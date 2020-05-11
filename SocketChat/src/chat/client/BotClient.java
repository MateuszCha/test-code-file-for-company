package chat.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    @Override
    protected chat.client.Client.SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        short x = (short) (Math.random()*100);
        return "date_bot_"+x;
    }
////////////////////////////////////////CLASS INNER////////////////
    public class BotSocketThread extends chat.client.Client.SocketThread {
    @Override
    protected void clientMainLoop() throws IOException, ClassNotFoundException {
       sendTextMessage( "Hello, there. I'm a bot. I understand the following commands: date, day, month, year, time, hour, minutes, seconds.");
       super.clientMainLoop();
    }

    @Override
    protected void processIncomingMessage(String message) {
        super.processIncomingMessage(message);

        if (message.contains(":")) {
            String[] sTab = message.split(": ");
            String pattern = null;
            switch (sTab[1]) {
                case "date":
                    pattern = "d.MM.YYYY";
                    break;
                case "day":
                    pattern = "d";
                    break;
                case "month":
                    pattern = "MMMM";
                    break;
                case "year":
                    pattern = "YYYY";
                    break;
                case "time":
                    pattern = "H:mm:ss";
                    break;
                case "hour":
                    pattern = "H";
                    break;
                case "minutes":
                    pattern = "m";
                    break;
                case "seconds":
                    pattern = "s";
                    break;
            }
            if (pattern != null) {
                String date = new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
                String result = "Information for " + sTab[0] + ": " + date;
                sendTextMessage(result);
            }
        }
    }

}

//////////////////////////////////////MAIN///////////////////////////
    public static void main(String[] args){
        BotClient botClient = new BotClient();
        botClient.run();

    }
}
