package project.basis;

import java.util.Scanner;

import project.hamster.Hamster;
import project.hamster.Territory;
import project.searchlogic.DepthFirstSearch;
import project.user.CurrentUser;

public class Client
{
	private CurrentUser user;

	/**
	 * Erstmaliges Anmelden und Instanz der Nutzerklasse definieren
	 */
	public Client()
	{
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

		System.out.println("Benutzernamen eingeben");
		tmpValue = inSignIn.nextLine();
		System.out.println("Passwort eingeben");
		user = new CurrentUser(tmpValue, inSignIn.nextLine());
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
		String initSave;
		Hamster hamster = new Hamster(user);	//Hamster Objekt erstellen

		initSave = hamster.init();
		if (!initSave.contains("Error"))
		{
			Territory territory = new Territory(initSave); //Territorium auslesen und ermitteln, dabei den Hamster mit initialisieren auf dem Feld
			DepthFirstSearch depthFirst = new DepthFirstSearch(hamster, territory);	//Hamsterprogramm erstellen
			depthFirst.searchCorn(); //Programm starten
		}
		else
		{
			System.out.println(initSave);
		}
	}
}
