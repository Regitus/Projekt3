package projekt.user;

/**
 * Anmeldedaten des Benutzers werden hier gespeichert.
 * !ACHTUNG! Passwort im Klartext intern tempor�r gesichert
 * @author David, Rene, Tim
 *
 */
public class AktuellerBenutzer {
	
	private String benutzerName;
	private String passwort;
	
	public AktuellerBenutzer(String name, String pass)
	{
		benutzerName = name;
		passwort = pass;
	}
	
	/**
	 * @return Gibt den Benutzernamen zur�ck
	 */
	public String getBenutzerName() {
		return benutzerName;
	}
	
	
	/**
	 * Passwort Variable: Achtung Klartext!
	 * 
	 * @return Speichert das Passwort
	 */
	public String getPasswort() {
		return passwort;
	}
}
