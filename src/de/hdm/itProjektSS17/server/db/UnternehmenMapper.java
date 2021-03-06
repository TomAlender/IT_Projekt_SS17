package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper fuer ein Unternehmen-Objekt
 */
public class UnternehmenMapper extends OrganisationseinheitMapper{

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static UnternehmenMapper unternehmenMapper = null;

    /**
     * Geschuetzter Konstruktor
     */
    protected UnternehmenMapper() {
        // TODO implement here
    }

    /**
     * @return unternehmenMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static UnternehmenMapper unternehmenMapper() {
        if(unternehmenMapper == null){
        	unternehmenMapper = new UnternehmenMapper();
        }
        return unternehmenMapper;
    }

    /**
     * @param id 
     * @return Liefert ein Unternehmen entsprechend der ï¿½bergebenen id zurueck
     */
    public Unternehmen findById(int id) {

    	Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Unternehmen_Id, Name"
					+ " FROM unternehmen " + "WHERE Unternehmen_Id=" + 
			id + " ORDER BY Name");
			
			
			if(rs.next()){
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("Unternehmen_Id"));
				u.setName(rs.getString("Name"));
				u.setStrasse(super.findById(id).getStrasse());
				u.setHausnummer(super.findById(id).getHausnummer());
				u.setOrt(super.findById(id).getOrt());
				u.setPlz(super.findById(id).getPlz());
				u.setPartnerprofilId(super.findById(id).getPartnerprofilId());
						
				return u;
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
     * @param u
     * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
     */
    public Unternehmen findByObject(Unternehmen u){
		return this.findById(u.getId());
    	
    	
    }
    
    public Vector<Unternehmen> findAllUnternehmen(){
    	Connection con = DBConnection.connection();
		
		Vector<Unternehmen> result = new Vector<Unternehmen>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM unternehmen ORDER BY Unternehmen_Id");
			
			
			while (rs.next()){
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("Unternehmen_Id"));
				u.setName(rs.getString("Name"));
				u.setStrasse(super.findByObject(u).getStrasse());
				u.setHausnummer(super.findByObject(u).getHausnummer());
				u.setPlz(super.findByObject(u).getPlz());
				u.setOrt(super.findByObject(u).getOrt());
				u.setPartnerprofilId(super.findByObject(u).getPartnerprofilId());
				
				result.add(u);
				}
			}  
		catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
	
	}


    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess des uebergebenen Objekts loeschen.
     */
    public void delete(Unternehmen u) {
        // TODO implement here
    	  Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM unternehmen " + "WHERE Unternehmen_Id=" + u.getId());
		      super.delete(u);
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }
    

    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess den Informationen des uebergebenen Objekts aktualisieren.
     */
    public Unternehmen update(Unternehmen u) {
    	 Connection con = DBConnection.connection();
		    
		    

		    try {
		    u.setId(super.update(u));
		     
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE unternehmen SET Name='" + u.getName() + "' "
		          + "WHERE Unternehmen_Id=" + u.getId());
		     
		     
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return u;
	  }
    

    /**
     * @param u 
     * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
     */
    public Unternehmen insert(Unternehmen u) {
    	
    	//DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	 //Leeres SQL-Statement anlegen
		        Statement stmt = con.createStatement();
		        
		       	u.setId(super.insert(u));
			   	//System.out.println(u.getId());       
		        //Leeres SQL-Statement fÃ¼r die Insert- AusfÃ¼hrung anlegen.

		        // Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
		        stmt.executeUpdate("INSERT INTO `unternehmen` (`Unternehmen_Id`, `Name`) "
		            + "VALUES ('" + u.getId() + "','" + u.getName() + "')");
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return u;
		  
	  }
    }

