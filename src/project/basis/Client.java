package project.basis;

import java.util.Scanner;

import projekt.hamster.Hamster;
import projekt.hamster.Territorium;
import projekt.messages.TextMessageHandler;
import projekt.searchlogic.DepthFirstSearch;
import projekt.user.CurrentUser;

public class Client
{

	private CurrentUser user; // = new CurrentUser();
	TextMessageHandler textMessages;

	/**
	 * Erstmaliges Anmelden und Instanz der Nutzerklasse definieren
	 */
	public Client() {
		signIn();

	}

	/**
	 * Neue Anmeldedaten speichern
	 */
	private void signIn()
	{
		String tmpValue;
		@SuppressWarnings("resource")
		Scanner inSignIn = new Scanner(System.in);
		
		inSignIn = new Scanner(System.in);
		System.out.println("Benutzernamen eingeben");
		tmpValue = inSignIn.nextLine();
		System.out.println("Passwort eingeben");
		user = new CurrentUser(tmpValue, inSignIn.nextLine());

		// Neu Anmelden in den MessageHandler Klassen
		textMessages = new TextMessageHandler(user.getUserName(), user.getPassword());
		
		// Neue Nutzerliste für den User holen

	}

	/**
	 * Das Hauptmenu des Programmes Weiterverlinkung zum Gruppenmenü und Sendenmenü
	 */

	public void program()
	{
		Scanner inMain = new Scanner(System.in);
		boolean run = true;
		do
		{
			System.out.println("\n1. Neue Anmeldedaten" + "\n2. Programm ausführen" + "\n99. Beenden");
			switch (inMain.nextInt())
			{
			case 1: // Neues anmelden
				signIn();
				break;

			case 2: // Startet das Hamsterprogramm
				startProgram();
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

	private void startProgram() {
		Hamster hamster = new Hamster(user);	//Hamster Objekt erstellen
		Territorium territorium = new Territorium(hamster.init()); //Territorium auslesen und ermitteln, dabei den Hamster mit initialisieren auf dem Feld
		DepthFirstSearch depthFirst = new DepthFirstSearch(hamster, territorium);	//Hamsterprogramm erstellen
		depthFirst.searchCorn(); //Programm starten
	}
}
