package projekt.hamster;

/*class*/public class SearchHamster// extends Hamster 
{
	
	
	int ausrichtung = 1;
	public static final int norden = 0;
	public static final int osten = 1;
	public static final int sueden = 2;
	public static final int westen = 3;
	
	/**
	 * Die Reihe und die Spalte des Startfeldes, sowie die Richtung muss übergeben werden.
	 * Die Anzahl der Körner im Mund wird standardisiert auf null gesetzt.
	 * @param row Die Reihe des Feldes, auf dem sich der Hamster nach der Initialisierung befindet.
	 * @param column Die Spalte des Feldes, auf dem sich der Hamster nach der Initialisierung befindet.
	 * @param direction Die Richtung, in die der Hamsta nach der Initialisierung blickt.
	 */
    public SearchHamster(int row, int colum, int direction) 
    {
		//super(row, colum, direction, 0);
	}
    
    
	
	/**
	 * Der Hamster dreht sich in die als Parameter spezifizierte Richtung.
	 * @param direction Die Richtung, wo sich der Hamster beim Laufen hin bewegt.
	 */
	public void turnToDirection(int direction)
	{
		while(direction != ausrichtung )
		{
			//Schreibe turnLeft();
		}
	}
	
	public void vor()
	{
		//Schreibe vor
	}
	
	public int getReihe()
	{
		//Gebe Reihe zurück
		return 3;
	}
	
	public int getSpalte()
	{
		//Gebe Spalte zurück
		return 3
	}
}
