package projekt.suchlogik;

import projekt.hamster.SearchHamster;

/*class*/class DepthFirstSearch
{
	private FieldMaster fieldMaster;
	private SearchHamster hamster;
	
	/**
	 * Initialisert die für die Tiefesuche relevanten Attribute. 
	 * @param Der SearchHamster, der nach dem Start die Tiefesuche nachläuft.
	 */
    public DepthFirstSearch(SearchHamster hamster)
    {
    	this.fieldMaster = new FieldMaster();
    	this.hamster = hamster;
    }
    
    /**
     * Startet die Hamstersuche.
     * @return Gibt ein Wert zurück, ob ein Korn gefunden wurde.
     */
    public boolean searchCorn()
    {
    	return searchCorn(-1);
    }
    
    /**
     * Alle Richtungen des Feldes, wo sich der Hamster gerade befindet, werden untersucht.
     * @param direction Die Richtung, in die der Hamster bisher gelaufen ist.
     * @return Gibt ein Wert zurück, ob ein Korn gefunden wurde. 
     */
    private boolean searchCorn(int directionFrom)
    {
    	int row = hamster.getReihe();
    	int column = hamster.getSpalte();
    	   	
    	if(fieldMaster.isCornInField(row, column))
    	{
    		//hamster.pickGrain();
    		return true;
    	}
    	fieldMaster.visiting(row, column); 
    	
    	// Alle vier Richtungen werden untersucht.
		for(int direction = SearchHamster.norden; direction <= SearchHamster.westen; direction++)
		{
			// Überprüft, ob der Hamster das Nachbarfeld noch nicht untersucht hat.
			// Außerdem muss das Nachbarfeld im Territorium liegen und es darf sich keine Mauer befinden.
			if(canDiscoverField(row, column, direction))
			{
				hamster.turnToDirection(direction);
				hamster.vor();
				if(searchCorn(direction))
				{
				    // Das Korn wurde gefunden und die Rekursion wird aufgelöst, die der Hamster dann nicht mehr nachläuft,
    				// sodass er auf dem Korn-Feld bleibt. 
					return true;
				}
			}
		}
    	
    	// Alle Richtungen wurden untersucht, ohne ein Korn gefunden zu haben und der Hamster läuft wieder zurück.
    	back(directionFrom);
    	return false;
    }
    
    /**
     * Der Hamster läuft wieder zurück, wo er hergekommen ist.
     */
    private void back(int directionFrom)
    {
        if(directionFrom >= 0)
    	{
    		hamster.turnToDirection((directionFrom + 2) % 4);
    		hamster.vor();
    	}
    }
    
    /**
     * Gibt ein Wert zurück, der besagt, ob der Hamster das Nachbarfeld untersuchen darf.
     * Dieses Nachbarfeld wird mit der Spalte und Reihe des schon untersuchten Feldes, sowie die Richtung definiert.
     */
	private boolean canDiscoverField(int row, int column, int direction)
	{
		switch(direction)
		{
			case SearchHamster.norden:
				return fieldMaster.canBeDiscovered(row - 1, column);
	
			case SearchHamster.osten:
				return fieldMaster.canBeDiscovered(row, column + 1);
				
			case SearchHamster.sueden:
				return fieldMaster.canBeDiscovered(row + 1, column);
				
			case SearchHamster.westen:
				return fieldMaster.canBeDiscovered(row, column - 1);				
		}
		
		return false;
	}
}