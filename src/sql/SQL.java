/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Iterator;
/**T‰m‰ luokka toimii SQL-wrapperina muulle ohjelmistolle
 *
 * @author Toni Nurmi, Mette Winter
 * @version 1.0
 */
public class SQL {
    private Connection connection = null;
    private String database, user, password;
	private ArrayList commands;

    public String getDatabase() {
        return database;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public SQL(){
        //this.database = "s1100910";
        //this.user = "s1100910";
        //this.password = "em_7uIYo";
    	this.database = "budjetti_app";
        this.user = "root";
        this.password = "1234";
    }
    /**Kirjautuu sis‰‰n tietokantaan annetuilla tunnuksilla
     * @param database	Tietokannan nimi (case-sensitive)
     * @param user		Tietokannan p‰‰k‰ytt‰j‰n nimi (yleens‰ "root")
     * @param password	Tietokannan salasana (tyhj‰, ellei muuten m‰‰ritelty)
     */
    public SQL(String database, String user,String password){
            this.database = database;
            this.user = user;
            this.password = password;
}       

    
    /**T‰m‰ luokka avaa yhteyden tietokantaan
     * @throws Exception
     */
    public void connectToDatabase() throws Exception{
        try{
        	
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database, user, password);
           
        }
        catch(Exception e){
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database, user, "");
            e.printStackTrace();
        }
    }
 
    /**Kirjoitetaan tuntematon m‰‰r‰ uusia tietueita parametrin‰ annettuun tietokantatauluun
     * ArrayList tietueiden pit‰‰ olla kesken‰‰n samassa j‰rjestyksess‰.
     * 
     * Esim: 	identifiers	["nimi", "ika"]
     * 			values		["Toni", "25"]
     * 			valueTypes	["string", "int"]
     * 
     * @param table			Tietokantataulu, johon uudet arvot tallennetaan
     * @param identifiers	Tietokantataulun tietueiden nimet ArrayList-taulukossa
     * @param values		Tietokantataulun tietueiden arvot ArrayList-taulukossa
     * @param valueTypes	Tietokantataulun tietueiden tyypit (string, int, double) ArrayList-taulukossa
     * @throws Exception
     */
    public void insertValuesIntoDB(String table, ArrayList<String> identifiers, ArrayList <String> values, ArrayList <String> valueTypes) throws Exception{
    	
    	String headersText = "(";
    	String valueText = "(";
    	Iterator<String> it = identifiers.iterator();
    	while(it.hasNext()) {
    		headersText = headersText.concat(it.next() + ", ");
    		valueText = valueText.concat("?,");
    	}

    	if(headersText.endsWith(" ")){
    		headersText = headersText.substring(0, headersText.length()-2);
    	}
    	if(valueText.endsWith(",")){
    		valueText = valueText.substring(0, valueText.length()-1);
    	}
    	headersText = headersText.concat(")");
    	valueText = valueText.concat(")");
    	
    	System.out.println(headersText);
    	System.out.println(valueText);
    	
    	String writeSQL = "insert into " + table + " " + headersText + " values" + valueText;
    	PreparedStatement write = connection.prepareStatement(writeSQL);
    	
    	for(int i=0;i<identifiers.size();i++){
    		int num = i+1;
    		
    		if(valueTypes.get(i).equals("string")){
    			//System.out.println("i = " + i + " value: "+ values.get(i));
    			write.setString(num, (String) values.get(i));
    		}
    		else if(valueTypes.get(i).equals("int")){
    			//System.out.println("i = " + i + " value: "+ values.get(i));
    			write.setInt(num, Integer.parseInt(values.get(i)) );
    		}
    		else if(valueTypes.get(i).equals("double")){
    			//System.out.println("i = " + i + " value: "+ values.get(i));
    			write.setDouble(num, Double.parseDouble(values.get(i)));
    		}
    		
    	}
    	write.executeUpdate();

        // After execution we have to close the statement
        write.close();
    	
    }

    /**Tuhoaa tietokannan taulusta tietueen:
     * "delete from " + from + " where " + target + " " + targetID
     * @param from			Tietokantataulukko, josta poistetaan jotain
     * @param target		Tietue (sarake), josta poistetaan
     * @param targetID		Poista kaikki tiedot, joissa esiintyy t‰m‰ teksti tai tietue
     * @throws SQLException
     */
    public void destroyFromDB(String from, String target, String targetID) throws SQLException{
    	String writeSQL = "delete from " + from + " where " + target + " " + targetID;
    	PreparedStatement write = connection.prepareStatement(writeSQL);
    	write.executeUpdate();
    	write.close();
    }
    public boolean closeConnection() {
      if (connection!=null) {
        try {
          connection.close();
          return true;
        }
        catch (SQLException sqle) {
          return false;
        }
      }
      else {
        return false;
      }
    }
    public void closeStatement(Statement closeThisStatement) {
      if (closeThisStatement!=null) {
        try {
          closeThisStatement.close();
        }
        catch (SQLException sqle) {
          //t‰ss‰ ei ole mit‰‰n teht‰viss‰
        }
      }
    }
    /**Suorittaa annetun SQL-kyselyn
     * @param SQL_statement		Suoritettava kysely
     * @return					Palauttaa taulukkomuodossa kyselyn tuloksen
     * @throws Exception
     */
    public ResultSet readFrom_DB(String SQL_statement) throws Exception{
        
        
        // Let's use PrepareStatement even though in this particular case we are not subjective to a SQL injection
            PreparedStatement read = connection.prepareStatement(SQL_statement);
        

            // After we have prepared the SQL statement we can execute the query
            ResultSet results = read.executeQuery();
            
            //closeStatement(read);
            return results;
    }
    
    /**Kirjoittaa annetun SQL-lauseen
     * @param SQL_statement		Mik‰ tahansa tietokantaan tallennettava lause
     * @throws Exception
     */
    public void writeSqlStatement(String SQL_statement) throws Exception{
    	PreparedStatement write = connection.prepareStatement(SQL_statement);
    	write.executeUpdate();
    	write.close();
    }
    
        
    
    
}
