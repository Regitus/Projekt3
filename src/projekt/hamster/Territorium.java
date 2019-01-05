package projekt.hamster;

import projekt.nachrichten.TextSender;

public class Territorium {

	private String[][] felder;

	public void initialisieren(TextSender sender)
	{
		String s = getTerritorium(sender);
		String[] elemente = s.split(" ");

		int anzahlSpalten = Integer.parseInt(elemente[0]);
		int anzahlReihen = Integer.parseInt(elemente[1]);

		felder = new String[anzahlReihen][anzahlSpalten];

		for (int i = 0; i < elemente.length - 2; i++)
		{
			String feld = elemente[i + 2];
			int reihe = i / anzahlSpalten;
			int spalte = i % anzahlSpalten;

			felder[reihe][spalte] = feld;
		}
	}

	public int getAnzahlReihen() { return felder.length; }

	public int getAnzahlSpalten() { return felder[0].length; }
	
	public boolean mauerDa(int reihe, int spalte) { return felder[reihe][spalte] == "x"; }
	
	public int getAnzahlKoerner(int reihe, int spalte)
	{
		return (felder[reihe][spalte] == "!") ? 1 : 0;
	}

	private String getTerritorium(TextSender sender)
	{
		return "6 3 0 0 0 x 0 ! 0 x 0 x 0 x 0 x 0 0 0 x";
	}
}
