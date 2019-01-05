package projekt.basis;

import java.util.InputMismatchException;
import java.util.Scanner;

import projekt.nachrichten.TextSender;
import projekt.user.*;

public class Klient {

	private AktuellerBenutzer benutzer; // = new AktuellerBenutzer();
	TextSender textSenden;

	/**
	 * Erstmaliges Anmelden und Instanz der Nutzerklasse definieren
	 */
	public Klient() {
		anmelden();

	}

	/**
	 * Neue Anmeldedaten speichern
	 */
	private void anmelden() {
		String tmpWert;
		@SuppressWarnings("resource")
		Scanner inAnmelden = new Scanner(System.in);

		inAnmelden = new Scanner(System.in);
		System.out.println("Benutzernamen eingeben");
		tmpWert = inAnmelden.nextLine();
		System.out.println("Passwort eingeben");
		benutzer = new AktuellerBenutzer(tmpWert, inAnmelden.nextLine());

		// Neu Anmelden in den Sender Klassen
		textSenden = new TextSender(benutzer.getBenutzerName(), benutzer.getPasswort());

		// Neue Nutzerliste f�r den User holen

	}

	/**
	 * Das Hauptmenu des Programmes Weiterverlinkung zum Gruppenmen� und Sendenmen�
	 */

	public void programm() {
		Scanner inMain = new Scanner(System.in);
		boolean run = true;
		do {
			System.out.println("\n1. Neue Anmeldedaten" + "\n2. Die letzten 100 Nachrichten"
					+ "\n3. Neuer als eine bestimmte ID ausgeben" + "\n4. Senden Men�" + "\n5. Gruppenverwaltung"
					+ "\n6. Alle Nutzer ausgeben" + "\n99 Beenden");
			switch (inMain.nextInt()) {
				case 1: // Neues anmelden
					anmelden();
					break;

				case 2: // Gibt die letzten 100 Nachrichten aus
					ausgebenNeuesteNachrichten();
					break;

				case 3: // Neuer als eine bestimmte ID
					ausgebenNeuerAlsIDNachrichten();
					break;

				case 99: // Programm beenden
					inMain.close();
					run = false;
					break;

				default:
					break;
			}

		} while (run);
	}

	/**
	 * Startmethode um die bis zu 100 letzten Nachrichten auszugeben
	 */
	private void ausgebenNeuesteNachrichten() {
		String[] tmpString;

		// Holen der Nachrichten
		tmpString = textSenden.getNachrichten();
		ausgebenStringArray(tmpString);
	}

	/**
	 * Gibt Nachrichten aus die neuer sind als eine eingegebene ID
	 */
	private void ausgebenNeuerAlsIDNachrichten() {

		String[] tmpString;
		@SuppressWarnings("resource")
		Scanner inAlsID = new Scanner(System.in);

		System.out.println("Bitte geben sie eine Nachrichten ID an. Von dieser ID an, werden alle neueren angezeigt.");
		try {
			// Nachrichten neuer als ID holen
			tmpString = textSenden.getIDNachrichten(inAlsID.nextLong());
			ausgebenStringArray(tmpString);
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("Falsche Eingabe, Ausgabe abgebrochen");
		}
	}

	/**
	 * Methode zum allgemeinen Ausgeben eines String Array auf der Konsole
	 *
	 * @param arString; Array eines String �bergeben und wird auf der Konsole
	 *                  �bergeben
	 */
	private void ausgebenStringArray(String[] arString) {
		if (arString != null) {
			for (String text : arString) {
				System.out.println(text);
			}
		}
	}
}

