package projekt.hamster;

public class Territorium {
	
	private int anzahlReihe, anzahlSpalte;
	private String[][] feld;
	
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
	
	public int getAnzahlReihen()
	{
		return anzahlReihe;
	}
	
	public int getAnzahlSpalten()
	{
		return anzahlSpalte;
	}
	
	public boolean mauerDa(int reihe, int spalte)
	{
		if (feld[reihe][spalte].equals("x"))
		{
			return true;
		}
		return false;
	}
	
	public int getAnzahlKoerner(int reihe, int spalte)
	{
		if (feld[reihe][spalte].equals("!"))
		{
			return 1;
		}
		return 0;
	}

}
