package project.messages;

import java.io.IOException;

public class TextMessageHandler extends MessageHandler
{
	/**
	 * Anmeldedaten abspeichern
	 * @param userName Benutzername
	 * @param password	Passwort
	 */
    public TextMessageHandler(String userName, String password)
    {
        super(userName, password);
    }

    @Override
    protected boolean sendToServer(String receiver, String data) throws IOException, IllegalArgumentException
    {
        server.sendTextMessage(userName, password, receiver, data);
        return true;
    }
    
    /**
	 * Liest die 100 letzten Nachrichten aus und gibt sie als String Array zurück
	 * 
	 * @return String[]; String Array mit bis zu 100 Einträgen in der ausgehende und
	 *         eingehene Nachrichten des User gelistet sind
	 */
	public String[] getMessages()
	{
		try
		{
			return server.getMostRecentMessages(userName, password);

		} catch (IllegalArgumentException | IOException e)
		{
			return new String[] {"Fehler beim Anmelden"};
		}

	}
	
	/**
	 * 
	 * @param id Die eingegebene ID von der aus alle Nachrichten angezeigt werden die neuer sind
	 * @return String Array mit den Nachrichten
	 */
	public String[] getIDMessages(long id)
	{
		try
		{
			return server.getMessages(userName, password, id);
		}
		catch (IllegalArgumentException | IOException e)
		{
			return new String[] {"Fehler beim Anmelden"};
		}
		

	}
}