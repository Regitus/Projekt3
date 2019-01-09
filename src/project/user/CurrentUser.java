package project.user;

/**
 * Anmeldedaten des Benutzers werden hier gespeichert.
 * !ACHTUNG! Passwort im Klartext intern temporär gesichert
 * @author David, Rene, Tim
 *
 */
public class CurrentUser {
	
	private String userName = "";
	private String password = "";
	
	public CurrentUser(String name, String pass)
	{
		userName = name;
		password = pass;
	}
	
	/**
	 * @return Gibt den Benutzernamen zurück
	 */
	public String getUserName() {
		return userName;
	}
	
	
	/**
	 * Passwort Variable: Achtung Klartext!
	 * 
	 * @return Speichert das Passwort
	 */
	public String getPassword() {
		return password;
	}
}
