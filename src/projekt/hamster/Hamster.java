package projekt.hamster;

import projekt.nachrichten.TextServerVerbindung;
import projekt.user.AktuellerBenutzer;

/*class*/public class Hamster
{

	int ausrichtung = 1;
	public static final int norden = 0;
	public static final int osten = 1;
	public static final int sueden = 2;
	public static final int westen = 3;
	int reihe, spalte, maxReihe, maxSpalte;

	TextServerVerbindung textNachrichten;

	/**
	 * Die Reihe und die Spalte des Startfeldes, sowie die Richtung muss übergeben
	 * werden. Die Anzahl der Körner im Mund wird standardisiert auf null gesetzt.
	 * 
	 * @param row       Die Reihe des Feldes, auf dem sich der Hamster nach der
	 *                  Initialisierung befindet.
	 * @param column    Die Spalte des Feldes, auf dem sich der Hamster nach der
	 *                  Initialisierung befindet.
	 * @param direction Die Richtung, in die der Hamsta nach der Initialisierung
	 *                  blickt.
	 */
	public Hamster(AktuellerBenutzer anmeldeDaten)
	{
		// super(row, colum, direction, 0);
		// Hier Init befehl
		textNachrichten = new TextServerVerbindung(anmeldeDaten.getBenutzerName(), anmeldeDaten.getPasswort());

	}

	/**
	 * Der Hamster dreht sich in die als Parameter spezifizierte Richtung.
	 * 
	 * @param direction Die Richtung, wo sich der Hamster beim Laufen hin bewegt.
	 */
	public void dreheBisAusrichtung(int blick)
	{
		while (blick != ausrichtung)
		{
			// Schreibe turnLeft();
			linksUm();
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
		reihe = spalte = 0;
		
		String[] nachrichten, getrennteNachricht;
		textNachrichten.senden("hamster18ws", "init");
		try
		{
			// 5 Versuche um die Eingangsnachricht zu finden
			for (int i = 0; i < 5; i++)
			{
				Thread.sleep(3000);
				nachrichten = textNachrichten.getNachrichten();
				getrennteNachricht = nachrichten[nachrichten.length-1].split("\\|");
				if (getrennteNachricht[2].equals("in")&& getrennteNachricht[3].equals("hamster18ws"))
				{
					//Variabel nachrichten missbrauchen um Feldgröße zu bekommen
					nachrichten = getrennteNachricht[5].split(" ");
					maxReihe = Integer.parseInt(nachrichten[2]);
					maxSpalte = Integer.parseInt(nachrichten[1]);;
					return getrennteNachricht[5];
				}
			}

		} catch (InterruptedException e)
		{
			return "Fehler beim warten auf Init Rückgabe!";
		}
		return "Es konnte nicht initialisiert werden!";

	}

	public void vor()
	{
		// Schreibe vor
		textNachrichten.senden("hamster18ws", "vor");
		switch (ausrichtung)
		{
		case Hamster.norden:
			if (reihe != 0)
			{
				reihe--;
			}
			break;
		case Hamster.osten:
			if (spalte < maxSpalte)
			{
				spalte++;
			}
			break;
		case Hamster.sueden:
			if (reihe < maxReihe)
			{
				reihe++;
			}
			break;
		case Hamster.westen:
			if (spalte != 0)
			{
				spalte--;
			}
			break;
		}
	}

	public int getReihe()
	{
		// Gebe Reihe zurück
		return reihe;
	}

	public int getSpalte()
	{
		// Gebe Spalte zurück
		return spalte;
	}

	public void linksUm()
	{
		textNachrichten.senden("hamster18ws", "linksUm");
		ausrichtung = (ausrichtung + 3) % 4;
	}

	public void nimm()
	{
		textNachrichten.senden("hamster18ws", "nimm");
	}	
}
