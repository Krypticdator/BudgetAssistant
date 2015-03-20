package sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**T‰m‰ on Kontrolliluokka SQL-tietorakenteen, ja muun ohjelman v‰lill‰
 * @author Toni Nurmi, Mette Winter
 * @version 1.0
 *
 */
public class SQL_Controller {
	private SQL kanta;
	private ArrayList<String> headers, values, types; //Used when writing to database
	
	
	public SQL_Controller(){
		try{
			kanta = new SQL();
			kanta.connectToDatabase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		headers = new ArrayList<String>();
		values = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	/**Luo tietokannan annetuilla arvoilla
	 * @param database	Tietokannan nimi (case sensitive)
	 * @param user		Tietokannan p‰‰k‰ytt‰j‰(yleens‰ "root")
	 * @param password	salasana
	 */
	public SQL_Controller(String database, String user, String password){
		try{
			kanta = new SQL(database, user, password);
			kanta.connectToDatabase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		headers = new ArrayList<String>();
		values = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	/**Tuhoaa kaikki aikaisemmat taulukoissa olevat arvot
	 * T‰m‰ funktio kutsutaan aina sen j‰lkeen, kun sql-tietokantaan 
	 * on tehty kaikki odottavat muutokset
	 * 
	 */
	public void initArrayLists(){
		headers.clear();
		values.clear();
		types.clear();
	}
	/**Uudet arvot tallennetaan taulukoihin (muistiin) odottamaan tallentamista tietokantaan
	 * 
	 * 
	 * @param header	Tietueen nimi
	 * @param value		Tietueen arvo
	 * @param type		tietueen tyyppi (int, string, double)
	 */
	public void addValue(String header, String value, String type){
		this.headers.add(header);
		this.values.add(value);
		this.types.add(type);
	}
	/**Tallentaa odottavat  arvot pysyv‰sti tietokantaan
	 * @param table		Muutokset tallennetaan t‰h‰n sql-taulukkoon
	 */
	public void commitValues(String table){
		try {
			kanta.insertValuesIntoDB(table, headers, values, types);
			initArrayLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 /**Tallentaa uuden k‰ytt‰j‰n tietokantaan
	 * @param user			K‰ytt‰j‰nimi
	 * @param password		K‰ytt‰j‰n salasana
	 * @throws Exception	
	 */
	public void writeToUserTable(String user, String password) throws Exception{
			
		 	initArrayLists();
		 	addValue("name",user,"string");
		 	addValue("password", password, "string");
		 	commitValues("users");
	       
	 }
	 /**Varmentaa k‰ytt‰j‰nimen ja salasanan oikeellisuuden
	 * @param user			K‰ytt‰j‰n syˆtt‰m‰ k‰ytt‰j‰nimi
	 * @param password		K‰ytt‰j‰n syˆtt‰m‰ salasana
	 * @return				Palauttaa arvon tosi, jos k‰ytt‰j‰ + salasana yhdistelm‰ t‰sm‰‰ tietokannan vastaaviin
	 * @throws Exception
	 */
	public boolean verifyUser(String user, String password)throws Exception{
	    	boolean isCorrect = false;
	    	ResultSet results = kanta.readFrom_DB("select * from users order by name desc");
	        
	        while (results.next()) {
	        	if(results.getString(2).equals(user)&&results.getString(3).equals(password)){
	        		isCorrect = true;
	            	}
	            }
	       return isCorrect;
	 }
	 /**Palauttaa k‰ytt‰j‰nime‰ vastaavan primary-avaimen tietokannasta
	  * sql-taulukoista hakemista varten
	 * @param user			K‰ytt‰j‰nimi
	 * @return				Palauttaa k‰ytt‰j‰‰ vastaavan yksilˆllisen avaimen
	 * @throws Exception
	 */
	public int getUserKey(String user)throws Exception{
	    	ResultSet results = kanta.readFrom_DB("select * from users order by name desc");
	        int user_key =0;
	        
	        while (results.next()) {
	        	if(results.getString(2).equals(user)){
	        		user_key = results.getInt(1);
	        	}
	        }
	        return user_key;
	 }
	
	public boolean isUserInDataBase(String user){
		boolean isInUse=false;
		
		try {
			ResultSet results = kanta.readFrom_DB("select * from users order by name desc");
			while(results.next()){
				if(results.getString(2).equals(user)){
					isInUse=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isInUse;
		
	}
	 
	 /**Tallentaa uuden budjetti-tapahtuman tietokantaan
	 * @param user		Tapahtuman tehnyt k‰ytt‰j‰
	 * @param tunniste	Tapahtuman tunniste, esim: vuokra
	 * @param summa		Tapahtuman suuruus euroissa
	 */
	public void insertSum(String user, String tunniste, String summa){
	    	try {
				int userId=getUserKey(user);
				initArrayLists();
				System.out.println("user_id: " + userId + " tunniste: " +tunniste + " summa: " +summa);
				addValue("user_id", String.valueOf(userId), "int");
				addValue("tunniste", tunniste, "string");
				addValue("summa", summa, "double");
				commitValues("rahatapahtuma");
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 /**Palauttaa samalla tunnisteella tehtyjen tapahtumien kokonaissumman
	 * @param userkey		K‰ytt‰j‰n primary-key tietokannassa
	 * @param tunniste		Etsitt‰v‰ tunniste
	 * @return				Palauttaa kaikkien tunnisteen jakavien tapahtumien rahallisen summan
	 * @throws Exception
	 */
	public double getSum(int userkey, String tunniste)throws Exception{
	    	double sum =0;
	    	ResultSet results = kanta.readFrom_DB("select * from rahatapahtuma order by tunniste desc");
	        while (results.next()) {
	        	if(results.getInt(2)==userkey && results.getString(3).equals(tunniste)){
	        		sum += results.getDouble(4);
	        	}
	        }
	        return sum;
	      
	 }

	 /**Palauttaa joko negatiiviset tai positiiviset raha-tapahtumat
	 * @param userkey	K‰ytt‰j‰n primary-key tietokannassa
	 * @param income	Haetaanko tuloja(true) vai menoja(false)?
	 * @return			Palauttaa tulojen tai menojen summan
	 * @throws Exception
	 */
	public double getEvent(int userkey, boolean income) throws Exception{
		 double sum = 0;
		 ResultSet results = kanta.readFrom_DB("select * from rahatapahtuma order by tunniste desc");
		 
		 while(results.next()){
			 if(income){
				 if(results.getInt(2)==userkey && results.getDouble(4)>0){
		        		sum += results.getDouble(4);
		         }
			 }
			 else{
				 if(results.getInt(2)==userkey && results.getDouble(4)<0){
		        		sum -= results.getDouble(4);
		         }
			 }
		 }
		 return sum;
		 
	 }
	 /**
	 * @param Tuhoaa k‰ytt‰j‰numeron kaikki tapahtumat tietokannasta
	 * @throws SQLException
	 * @throws Exception
	 */
	public void destroyAllEvents(int user) throws SQLException, Exception{
	    	String writeSQL = "delete from rahatapahtuma where user_id = " + user;
	    	kanta.writeSqlStatement(writeSQL);
	 }
	 public void closeDBConnection(){
		 kanta.closeConnection();
	 }
	 public void createDatabase() throws Exception{
		 try{
			 String sql = "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";"
			 +"SET time_zone = \"+00:00\";"


			 +"/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;"
			 +"/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;"
			 +"/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;"
			 +"/*!40101 SET NAMES utf8 */;"

			 +"CREATE DATABASE `budjetti_app` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;"
			 +"USE `budjetti_app`;"


			 +"DROP TABLE IF EXISTS `erittelyt`;"
			 +"CREATE TABLE IF NOT EXISTS `erittelyt` ("
			 +  "`erittely_id` int(11) NOT NULL,"
			 +  "`summa_id` int(11) NOT NULL,"
			 +  "`tunniste` varchar(40) NOT NULL,"
			 +  "`summa` double NOT NULL,"
			 +  "PRIMARY KEY (`erittely_id`),"
			 +  "KEY `summa_id` (`summa_id`)"
			 +") ENGINE=InnoDB DEFAULT CHARSET=latin1;"

/*			 -- --------------------------------------------------------

			 --
			 -- Rakenne taululle `example_autoincrement`
			 --
*/
			 +"DROP TABLE IF EXISTS `example_autoincrement`;"
			 +"CREATE TABLE IF NOT EXISTS `example_autoincrement` ("
			  +"`id_users` int(11) NOT NULL AUTO_INCREMENT,"
			  +"`name` varchar(100) DEFAULT NULL,"
			  +"`password` varchar(100) DEFAULT NULL,"
			  +"PRIMARY KEY (`id_users`)"
			 +") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"
/*
			 -- --------------------------------------------------------

			 --
			 -- Rakenne taululle `rahatapahtuma`
			 --
*/
			 +"DROP TABLE IF EXISTS `rahatapahtuma`;"
			 +"CREATE TABLE IF NOT EXISTS `rahatapahtuma` ("
			   +"`meno_id` int(11) NOT NULL AUTO_INCREMENT,"
			   +"`user_id` int(11) NOT NULL,"
			   +"`tunniste` varchar(40) NOT NULL,"
			   +"`summa` double NOT NULL,"
			   +"PRIMARY KEY (`meno_id`),"
			   +"KEY `user_id` (`user_id`)"
			 +") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;"
			 +"DROP TABLE IF EXISTS `rahatapahtuma2_autoincrement`;"
			 +"CREATE TABLE IF NOT EXISTS `rahatapahtuma2_autoincrement` ("
			   +"`id_event` int(11) NOT NULL AUTO_INCREMENT,"
			   +"`user_id` int(11) DEFAULT NULL,"
			   +"`tunniste` varchar(100) DEFAULT NULL,"
			   +"`summa` double DEFAULT NULL,"
			   +"PRIMARY KEY (`id_event`)"
			 +") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"
/*
			 -- --------------------------------------------------------

			 --
			 -- Rakenne taululle `rahatapahtuma_autoincrement`
			 --
*/
			 +"DROP TABLE IF EXISTS `rahatapahtuma_autoincrement`;"
			 +"CREATE TABLE IF NOT EXISTS `rahatapahtuma_autoincrement` ("
			   +"`id_event` int(11) NOT NULL AUTO_INCREMENT,"
			   +"`user_id` int(11) DEFAULT NULL,"
			   +"`tunniste` varchar(100) DEFAULT NULL,"
			   +"`summa` double DEFAULT NULL,"
			   +"PRIMARY KEY (`id_event`)"
			 +") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"
/*
			 -- --------------------------------------------------------

			 --
			 -- Rakenne taululle `users`
			 --
*/
			 +"DROP TABLE IF EXISTS `users`;"
			 +"CREATE TABLE IF NOT EXISTS `users` ("
			   +"`user_id` int(11) NOT NULL AUTO_INCREMENT,"
			   +"`name` varchar(40) NOT NULL,"
			   +"`password` varchar(40) NOT NULL,"
			   +"PRIMARY KEY (`user_id`)"
			 +") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;"
			 +"DROP TABLE IF EXISTS `users_autoincrement`;"
			 +"CREATE TABLE IF NOT EXISTS `users_autoincrement` ("
			   +"`id_users` int(11) NOT NULL AUTO_INCREMENT,"
			   +"`name` varchar(100) DEFAULT NULL,"
			   +"`password` varchar(100) DEFAULT NULL,"
			   +"PRIMARY KEY (`id_users`)"
			 +") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"

			+"/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;"
			+" /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;"
			+" /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;";
			kanta.writeSqlStatement(sql);
		 }catch(SQLException eq){
			 eq.printStackTrace();
			 System.out.println("Tietokannan luonti ep‰onnistui");
		 }
		 try{
			 kanta.writeSqlStatement("CREATE TABLE rahatapahtuma_autoincrement (id_event INT NOT NULL AUTO_INCREMENT PRIMARY KEY, user_id INT, tunniste VARCHAR(100), summa DOUBLE)");
		 }catch(SQLException eq){
			 eq.printStackTrace();
		 }
	 }
		 
	
	
}
