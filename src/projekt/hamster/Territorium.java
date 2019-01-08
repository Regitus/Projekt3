package projekt.hamster;

public class Territorium {
	
	private int anzahlReihe, anzahlSpalte;
	private String[][] feld;
	
	/**
	 * 
	 * @param feldString String mit der Textrückgabe vom Hamster nach dem init Befehl
	 */
	public Territorium(String feldString)
	{
		String[] split = feldString.split(" ");
		anzahlReihe = Integer.parseInt(split[2]);
		anzahlSpalte = Integer.parseInt(split[1]);
		feld = new String[anzahlReihe][anzahlSpalte];
		int merkfeld = 3;
		for(int i = 0; i<anzahlReihe; i++)
		{
			for (int j = 0; j<anzahlSpalte; j++)
			{
				feld[i][j] = split[merkfeld+j];
			}
			merkfeld += anzahlSpalte;
		}
	}
	
	/**
	 * Anzahl der Gesamtreihen
	 * @return Anzahl der Reihen
	 */
	public int getAnzahlReihen()
	{
		return anzahlReihe;
	}
	/**
	 * Anzahl der Gesamtspalten
	 * @return Anzahl der Spalten
	 */
	public int getAnzahlSpalten()
	{
		return anzahlSpalte;
	}
	
	/**
	 * Prüft ob eine Mauer an der Koordinate ist. True wenn ja.
	 * @param reihe Int Wert der Reihe
	 * @param spalte Int Wert der Spalte
	 * @return Boolean; true bei ja, false bei nein
	 */
	public boolean mauerDa(int reihe, int spalte)
	{
		if (feld[reihe][spalte].equals("x"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Gibt die Anzahl der Körner auf der koordinate wieder
	 * @param reihe Int Wert der Reihe
	 * @param spalte Int Wert der Spalte
	 * @return int wert mit Korn
	 */
	public int getAnzahlKoerner(int reihe, int spalte)
	{
		if (feld[reihe][spalte].equals("!"))
		{
			return 1;
		}
		return 0;
	}

}
