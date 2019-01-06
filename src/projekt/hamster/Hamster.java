package projekt.hamster;

import projekt.nachrichten.TextSender;

public class Hamster
{
    public static final int NORDEN = 0;
    public static final int OSTEN = 1;
    public static final int SUEDEN = 2;
    public static final int WESTEN = 3;

    protected int ausrichtung = OSTEN;

    private int reihe = 0;
    private int spalte = 0;

    private TextSender sender;
    private String empfaenger;

    public Hamster(TextSender sender, String empfaenger)
    {
        this.sender = sender;
        this.empfaenger = empfaenger;
    }

    public void vor()
    {
        if(sender.senden(empfaenger, "vor"))
        {
            switch (ausrichtung)
            {
                case NORDEN:
                    reihe--;
                    break;

                case OSTEN:
                    spalte++;
                    break;

                case SUEDEN:
                    reihe++;
                    break;

                case WESTEN:
                    spalte--;
                    break;
            }
        }
    }

    public void linksUm()
    {
        if(sender.senden(empfaenger, "linksUm"))
        {
            ausrichtung = (ausrichtung + 3) % 4;
        }
    }

    public void nimm()
    {
        sender.senden(empfaenger, "nimm");
    }

    public int getReihe()
    {
        return reihe;
    }

    public int getSpalte()
    {
        return spalte;
    }
}
