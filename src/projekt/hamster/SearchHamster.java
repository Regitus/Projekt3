package projekt.hamster;

import projekt.nachrichten.TextSender;

public class SearchHamster extends Hamster
{
	/**
	 * Die Reihe und die Spalte des Startfeldes, sowie die Richtung muss �bergeben werden.
	 * Die Anzahl der K�rner im Mund wird standardisiert auf null gesetzt.
	 * @param row Die Reihe des Feldes, auf dem sich der Hamster nach der Initialisierung befindet.
	 * @param column Die Spalte des Feldes, auf dem sich der Hamster nach der Initialisierung befindet.
	 * @param direction Die Richtung, in die der Hamsta nach der Initialisierung blickt.
	 */
    public SearchHamster(TextSender sender, String empfaenger)
    {
		super(sender, empfaenger);
	}
	
	/**
	 * Der Hamster dreht sich in die als Parameter spezifizierte Richtung.
	 * @param direction Die Richtung, wo sich der Hamster beim Laufen hin bewegt.
	 */
	public void turnToDirection(int direction)
	{
		while(direction != ausrichtung)
		{
			linksUm();
		}
	}
}
