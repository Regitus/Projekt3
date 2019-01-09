package projekt.hamster;

import projekt.messages.TextMessageHandler;
import projekt.user.CurrentUser;

/*class*/public class Hamster
{

	private int currentDirection = 1;
	public static final int norden = 0;
	public static final int osten = 1;
	public static final int sueden = 2;
	public static final int westen = 3;
	private int row, column, maxRow, maxColumn;

	TextMessageHandler textMessages;

	/**
	 * Objekt der klasse TextMessageHandler initialisieren
	 * @param loginData Klasse CurrentUser mit gef�llten Benutzer + Passwort
	 */
	public Hamster(CurrentUser loginData)
	{
		textMessages = new TextMessageHandler(loginData.getUserName(), loginData.getPassword());
	}

	/**
	 * Der Hamster dreht sich in die als Parameter spezifizierte Richtung.
	 * 
	 * @param direction Die Richtung, wo sich der Hamster beim Laufen hin bewegt.
	 */
	public void turnToDirection(int direction)
	{
		while (direction != currentDirection)
		{
			turnLeft();
		}
	}

	/**
	 * Initialisiert den Hamster im Server des Hamsterprogrammes und gibt das Feld
	 * als String zur�ck
	 * 
	 * @return String, Gibt die zweite In nachricht des Hamsters wieder. Beinhaltet
	 *         den Aufbau des Feldes.
	 */
	public String init()
	{
		row = column = 0;
		
		String[] messages, splittedMessages;
		textMessages.send("hamster18ws", "init");
		try
		{
			// 5 Versuche um die Eingangsnachricht zu finden
			for (int i = 0; i < 5; i++)
			{
				Thread.sleep(3000);
				messages = textMessages.getMessages();
				splittedMessages = messages[messages.length-1].split("\\|");
				if (splittedMessages[2].equals("in")&& splittedMessages[3].equals("hamster18ws"))
				{
					//Variabel nachrichten missbrauchen um Feldgr��e zu bekommen
					messages = splittedMessages[5].split(" ");
					maxRow = Integer.parseInt(messages[2]);
					maxColumn = Integer.parseInt(messages[1]);;
					return splittedMessages[5];
				}
			}

		} catch (InterruptedException e)
		{
			return "Error/Fehler beim warten auf Init R�ckgabe!";
		}
		return "Error/Es konnte nicht initialisiert werden!";

	}
	
	/**
	 * Eines nach vorne bewegen
	 */
	public void move()
	{
		textMessages.send("hamster18ws", "vor");
		switch (currentDirection)
		{
		case Hamster.norden:
				row--;
			break;
		case Hamster.osten:
				column++;
			break;
		case Hamster.sueden:
				row++;
			break;
		case Hamster.westen:
				column--;
			break;
		}
	}
	
	/**
	 * Gibt die Reihe zur�ck
	 * @return Reihe als Int-Wert
	 */
	public int getRow()
	{
		// Gebe Reihe zur�ck
		return row;
	}
	
	/**
	 * Gibt die Spalte zur�ck
	 * @return Spalte als Int-Wert
	 */
	public int getColumn()
	{
		// Gebe Spalte zur�ck
		return column;
	}
	
	/**
	 * Dreht den Hamster nach links
	 */
	public void turnLeft()
	{
		textMessages.send("hamster18ws", "linksUm");
		currentDirection = (currentDirection + 3) % 4;
	}
	
	/**
	 * nimmt ein Korn auf, wenn vorhanden
	 */
	public void take()
	{
		textMessages.send("hamster18ws", "nimm");
	}	
}
