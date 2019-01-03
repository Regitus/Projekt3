package projekt.suchlogik;

import projekt.hamster.Territorium;

/*class*/class FieldMaster 
{  
    private Field[][] fields;
    
    private int amountRows;
    private int amountColumns;
    
    private Territorium territorium;
    
    /**
     * Das zweidimensionale Feld-Array wird intern bef�llt.
     */
    public FieldMaster() 
    {
    	// Es wird vermieden, dass die Anfrage (Anzahl der Reihen oder Spalten) auf der Benutzeroberfl�che st�ndig angezeigt wird. 
    	territorium = new Territorium();
    	amountRows = territorium.getAnzahlReihen();
    	amountColumns = territorium.getAnzahlSpalten();
    		
    	fillArray();
    }
    
    /**
     * �berpr�ft, ob das Feld betrachtet werden darf.
     * @param row Die Reihe des Feldes
     * @param column Die Spalte des Feldes
     * @return Gibt ein Wahrheitswert zur�ck, ob das Feld im Territorium liegt und es nicht schon betrachtet wurde.
     */
    public boolean canBeDiscovered(int row, int column)
    {
    	if(row < 0 || row >= amountRows || column < 0 || column >= amountColumns)
    	{
    		return false;
    	}
    	
    	return fields[row][column] != Field.DISCOVERED;
    }
    
    /**
     * Das Feld wird als untersucht gespeichert.
     * @param row Die Reihe des Felds
     * @param column Die Spalte des Felds
     */
    public void visiting(int row, int column)
    {
    	fields[row][column] = Field.DISCOVERED;
    }
    
    /**
     * �berpr�ft, ob sich ein Korn im Feld befindet.
     * @param row Die Reihe
     * @param column Die Spalte
     * @return Gibt den Wahrheitswert zur�ck.
     */
    public boolean isCornInField(int row, int column)
    {
    	return fields[row][column] == Field.CORN;
    }
    
    /**
     * Scannt jedes Feld des Territoriums und speichert die Art als Enum ab. 
     * Felder mit einer Mauer werden direkt als schon betrachtet gespeichert.
     */
    private void fillArray()
	{
		fields = new Field[amountRows][amountColumns];
		
		for(int row = 0; row < amountRows; row++)
		{
			for (int column = 0; column < amountColumns; column++)
			{
				if(territorium.mauerDa(row, column))
				{
					fields[row][column] = Field.DISCOVERED;
				}
				else if (territorium.getAnzahlKoerner(row, column) > 0)
				{
					fields[row][column] = Field.CORN;
				}
				else
				{
					fields[row][column] = Field.FREE;
				}
			}
		}
	}    
}