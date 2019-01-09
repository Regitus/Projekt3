package project.messages;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

import java.io.IOException;

public abstract class MessageHandler
{
    protected String userName;
    protected String password;

    protected BasicTHMChatServer server;
    
    /**
     * Anmeldedaten übergeben
     * @param userName 
     * @param password
     */
    protected MessageHandler(String userName, String password)
    {
        this.userName = userName;
        this.password = password;

        this.server = new BasicTHMChatServer();
    }
    
    /**
     * Logik zum versenden der Nachricht
     * @param receiver Empfänger Name als String
     * @param data	Text oder Dateipfad
     * @return True wenn gesendet, falls wenn nicht
     */
    public boolean send(String receiver, String data)
    {
        try
        {
            return sendToServer(receiver, data);
        }
        catch (IOException ex)
        {
            System.out.println("Keine Verbindung zum Server oder der Benutzername bzw. das Passwort ist falsch.");
            return false;
        }
    }

    protected abstract boolean sendToServer(String empfaenger, String daten) throws IOException, IllegalArgumentException;
}