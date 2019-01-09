package project.hamster;

import project.messages.TextMessageHandler;
import project.user.CurrentUser;

public class Hamster
{
	private int currentDirection = 1;
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	private int row, column;

	private TextMessageHandler textMessages;

	/**
	 * Objekt der klasse TextMessageHandler initialisieren
	 * @param loginData Klasse CurrentUser mit gefüllten Benutzer + Passwort
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
	 * als String zurück
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
				    // Farbe anzeigen
                    String[] colorMessage = messages[messages.length - 2].split("\\|");
					System.out.println(colorMessage[colorMessage.length - 1]);

					return splittedMessages[5];
				}
			}

		} catch (InterruptedException e)
		{
			return "Error/Fehler beim warten auf Init Rückgabe!";
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
		case Hamster.NORTH:
				row--;
			break;
		case Hamster.EAST:
				column++;
			break;
		case Hamster.SOUTH:
				row++;
			break;
		case Hamster.WEST:
				column--;
			break;
		}
	}
	
	/**
	 * Gibt die Reihe zurück
	 * @return Reihe als Int-Wert
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Gibt die Spalte zurück
	 * @return Spalte als Int-Wert
	 */
	public int getColumn()
	{
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
