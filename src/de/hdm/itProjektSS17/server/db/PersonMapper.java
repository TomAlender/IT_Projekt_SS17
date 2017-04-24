package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;

/**
 * Mapper für Person- Objekte
 */
public class PersonMapper {

	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static PersonMapper personMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected PersonMapper(){
	}
	
	/**
	 * @return personMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static PersonMapper personMapper() {
		    if (personMapper == null) {
		      personMapper = new PersonMapper();
		    }

		    return personMapper;
		  }
	  
	/**
	 *   
	 * @param id
	 * @return Liefert eine Person entsprechend der übergebenen id zurueck.
	 */
	public Person findById(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Person_Id, Anrede, Vorname, Nachname, Organisationseinheit_Id, Unternehmen_Id, Team_Id"
					+ " FROM person " + "WHERE Person_Id=" + 
			id + "ORDER BY Nachname");
			
			
			if(rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("Person_Id"));
				p.setAnrede(rs.getString("Anrede"));
				p.setVorname(rs.getString("Vorname"));
				p.setNachname(rs.getString("Nachname"));
				p.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				p.setTeamId(rs.getInt("Team_Id"));
				p.setStraße(rs.getString("Strasse"));
				p.setHausnummer(rs.getString("Hausnummer"));
				p.setOrt(rs.getString("Ort"));
				p.setPlz(rs.getInt("PLZ"));
				p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
				p.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				
				
				
				return p;
				} 
			}   
		catch (SQLException e) {
		e.printStackTrace();
		return null;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param p
	 * @return Liefert eine Person entsprechend des übergebenen Objekts zurueck.
	 */
	public Person findByObject(Person p){
		return p;
		
	}
	
	/**
	 * 
	 * @param teamId
	 * @return Liefert alle Personen des uebergebenen Teams zurueck.
	 */
	public Vector<Person> findByForeignTeamId(int teamId){
		return null;
		
	}
	/**
	 * 
	 * @param unternehmenId
	 * @return Liefer alle Personen des uebergebenen Unternehmens zurueck.
	 */
	public Vector<Person> findByForeignUnternehmenId(int unternehmenId){
		return null;
	}
	
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Person p){
		  Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM person " + "WHERE Person_Id=" + p.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Person update(Person p){
		  
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE person " + "SET Vorname=\""
		          + p.getVorname() + "\", " + "Nachname=\"" + p.getNachname() + "\" "
		          + "WHERE Person_Id=" + p.getId());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    // Um Analogie zu insert(Customer c) zu wahren, geben wir c zurück
		    return p;
		
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Person insert(Person p){
		return p;
		  
	  }
}
