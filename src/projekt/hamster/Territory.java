package projekt.hamster;

public class Territory
{
	private int numberOfRows, numberOfColumns;
	private String[][] field;
	
	/**
	 * @param fieldString String mit der Textrückgabe vom Hamster nach dem init Befehl
	 */
	public Territory(String fieldString)
	{
		String[] split = fieldString.split(" ");

		numberOfRows = Integer.parseInt(split[2]);
		numberOfColumns = Integer.parseInt(split[1]);

		field = new String[numberOfRows][numberOfColumns];

		int columnToRemember = 3;
		for(int i = 0; i<numberOfRows; i++)
		{
			for (int j = 0; j<numberOfColumns; j++)
			{
				field[i][j] = split[columnToRemember+j];
			}
			columnToRemember += numberOfColumns;
		}
	}
	
	/**
	 * Anzahl der Gesamtreihen
	 * @return Anzahl der Reihen
	 */
	public int getNumberOfRows()
	{
		return numberOfRows;
	}
	/**
	 * Anzahl der Gesamtspalten
	 * @return Anzahl der Spalten
	 */
	public int getNumberOfColumns()
	{
		return numberOfColumns;
	}
	
	/**
	 * Prüft ob eine Mauer an der Koordinate ist. True wenn ja.
	 * @param row Int Wert der Reihe
	 * @param column Int Wert der Spalte
	 * @return Boolean; true bei ja, false bei nein
	 */
	public boolean checkWall(int row, int column)
	{
		return field[row][column].equals("x");
	}
	
	/**
	 * Gibt die Anzahl der Körner auf der koordinate wieder
	 * @param row Int Wert der Reihe
	 * @param column Int Wert der Spalte
	 * @return int wert mit Korn
	 */
	public int getNumberOfCorns(int row, int column)
	{
		return field[row][column].equals("!") ? 1 : 0;
	}
}
