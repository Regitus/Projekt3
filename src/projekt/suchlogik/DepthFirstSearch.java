package projekt.suchlogik;

import projekt.hamster.SearchHamster;

public class DepthFirstSearch
{
	private FieldMaster fieldMaster;
	private SearchHamster hamster;
	
	/**
	 * Initialisert die f�r die Tiefesuche relevanten Attribute. 
	 * @param Der SearchHamster, der nach dem Start die Tiefesuche nachl�uft.
	 */
    public DepthFirstSearch(SearchHamster hamster, FieldMaster fieldMaster)
    {
    	this.fieldMaster = fieldMaster;
    	this.hamster = hamster;
    }
    
    /**
     * Startet die Hamstersuche.
     * @return Gibt ein Wert zur�ck, ob ein Korn gefunden wurde.
     */
    public boolean searchCorn()
    {
    	return searchCorn(-1);
    }
    
    /**
     * Alle Richtungen des Feldes, wo sich der Hamster gerade befindet, werden untersucht.
     * @param direction Die Richtung, in die der Hamster bisher gelaufen ist.
     * @return Gibt ein Wert zur�ck, ob ein Korn gefunden wurde. 
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
		for(int direction = SearchHamster.NORDEN; direction <= SearchHamster.WESTEN; direction++)
		{
			// �berpr�ft, ob der Hamster das Nachbarfeld noch nicht untersucht hat.
			// Au�erdem muss das Nachbarfeld im Territorium liegen und es darf sich keine Mauer befinden.
			if(canDiscoverField(row, column, direction))
			{
				hamster.turnToDirection(direction);
				hamster.vor();
				if(searchCorn(direction))
				{
				    // Das Korn wurde gefunden und die Rekursion wird aufgel�st, die der Hamster dann nicht mehr nachl�uft,
    				// sodass er auf dem Korn-Feld bleibt. 
					return true;
				}
			}
		}
    	
    	// Alle Richtungen wurden untersucht, ohne ein Korn gefunden zu haben und der Hamster l�uft wieder zur�ck.
    	back(directionFrom);
    	return false;
    }
    
    /**
     * Der Hamster l�uft wieder zur�ck, wo er hergekommen ist.
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
     * Gibt ein Wert zur�ck, der besagt, ob der Hamster das Nachbarfeld untersuchen darf.
     * Dieses Nachbarfeld wird mit der Spalte und Reihe des schon untersuchten Feldes, sowie die Richtung definiert.
     */
	private boolean canDiscoverField(int row, int column, int direction)
	{
		switch(direction)
		{
			case SearchHamster.NORDEN:
				return fieldMaster.canBeDiscovered(row - 1, column);
	
			case SearchHamster.OSTEN:
				return fieldMaster.canBeDiscovered(row, column + 1);
				
			case SearchHamster.SUEDEN:
				return fieldMaster.canBeDiscovered(row + 1, column);
				
			case SearchHamster.WESTEN:
				return fieldMaster.canBeDiscovered(row, column - 1);				
		}
		
		return false;
	}
}