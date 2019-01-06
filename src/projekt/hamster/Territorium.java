package projekt.hamster;

public class Territorium
{

	private String[][] felder;

	public Territorium(String territorium)
	{
		initialisieren(territorium);
	}

	private void initialisieren(String territorium)
	{
		String[] elemente = territorium.split(" ");

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
}
