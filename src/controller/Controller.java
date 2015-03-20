package controller;
import java.sql.SQLException;

import GUI.RaporttiGUI;
import GUI.KirjautumisGUI;
import GUI.PaavalikkoGUI;
import GUI.TulotGUI;
import GUI.MenotGUI;
import GUI.TilinluomisGUI;
import sql.SQL;
import GUI.ErittelyGUI;
import sql.SQL_Controller;

/**Ohjelman pääkontrolleri, joka toimii siltana SQL-kontrollerin, mallin (model) ja käyttöliittymän välillä
 * @author Toni Nurmi, Mette Winter
 * @version 1.0
 *
 */
public class Controller {
	private KirjautumisGUI loginWindow;
	private PaavalikkoGUI mainMenuWindow;
	private TulotGUI incomeWindow;
	private MenotGUI expensesWindow;
	private TilinluomisGUI createAccountWindow;
	private ErittelyGUI detailsWindow;
	private RaporttiGUI reportWindow;
	
	private SQL_Controller dbController;
	private String account;
	
	public Controller(){		
		try{
			dbController = new SQL_Controller();
			//dbController.createDatabase();
			
			loginWindow = new KirjautumisGUI(this);
		}
		catch(Exception er){
			er.printStackTrace();
		}
		
		
		
	}
	/**Avaa päävalikon käyttäjän kirjautuessa onnistuneesti
	 * @param account	Käyttäjän syöttämä käyttäjätili
	 * @param password	Käyttäjän antama salasana
	 * @return			Palauttaa tosi, jos käyttäjänimi + salasana on oikein
	 */
	public boolean login(String account, String password){
		boolean isSuccessfulLogin = false;
		try{
			//isSuccessfulLogin = database.verifyUser(account, password);
			isSuccessfulLogin = dbController.verifyUser(account, password);
			if(isSuccessfulLogin){
				mainMenuWindow = new PaavalikkoGUI(this);
				this.account = account;
			}
		}
		catch(SQLException eq){
			 try {
				dbController.createDatabase();
				isSuccessfulLogin = dbController.verifyUser(account, password);
				if(isSuccessfulLogin){
					mainMenuWindow = new PaavalikkoGUI(this);
					this.account = account;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Yritettiin luoda uutta db:tä");
				e.printStackTrace();
			}
		 }
	
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccessfulLogin;
	}
	/**Käärefunktio käyttöliittymälle
	 * Tallentaa tapahtuman tietokantaan
	 * @param tunniste	Tapahtuman tunniste, jolla sitä voidaan myöhemmin etsiä
	 * @param summa		Tapahtuman rahamäärä euroina
	 */
	public void saveSumToDatabase(String tunniste, double summa){
		try{
			//database.insertSum(account, tunniste, summa);
			dbController.insertSum(account, tunniste, String.valueOf(summa));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**Tuhoaa kaikki käyttäjän tapahtumat
	 * 
	 */
	public void destroyEvents(){
		try{
			int userId = dbController.getUserKey(account); 
			//database.destroyAllEvents(userId);
			dbController.destroyAllEvents(userId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**Rekisteröi uuden käyttäjän ohjelmalle tietokantaan
	 * @param user		Uusi käyttäjänimi
	 * @param password	Uusi salasana
	 */
	public boolean saveNewUserToDB(String user, String password){
		boolean isSuccessful = false;
		try{
			//database.writeToUserTable(user, password);
			//dbController.
			if(dbController.isUserInDataBase(user)){
				createAccountWindow.setErrorText("Tunnus on jo olemassa");
			}else{
				dbController.writeToUserTable(user, password);
				isSuccessful = true;
			}
		}
		catch(SQLException eq){
			 try {
				dbController.createDatabase();
				dbController.writeToUserTable(user, password);
				isSuccessful = true;
				
			} catch (Exception e) {
				System.out.println("yritettiin luoda uusi tietokanta");
				e.printStackTrace();
			}
		 }
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccessful;
	}
	/**Avaa Menot-Ikkunan
	 * 
	 */
	public void openExpensesWindow(){
		expensesWindow = new MenotGUI(this);
	}
	/**Avaa Luo-Tunnus -ikkunan
	 * 
	 */
	public void openCreateAccountWindow(){
		createAccountWindow = new TilinluomisGUI(this);
	}
	/**Avaa Tulot-ikkunan
	 * 
	 */
	public void openIncomeWindow(){
		incomeWindow = new TulotGUI(this);
	}
	/**Tuhoaa kirjautumis-ikkunan
	 * 
	 */
	public void destroyLoginWindows(){
		loginWindow.destroyFrame();
	}
	/**Palauttaa nykyisen käyttäjänimen
	 * @return	Käyttäjänimi
	 */
	public String getAccount() {
		return account;
	}
	/**Tallentaa onnistuneesti kirjautuneen käyttäjän nimen ohjelman ajon ajaksi
	 * @param account	Tämän hetkinen käyttäjä
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**Ei toimi oikein
	 * Erittely-ikkuna ruoka-ostosten tarkkaan setvimiseen
	 */
	public void openDetailsWindow(){
		detailsWindow = new ErittelyGUI();
	}
	/**Avaa Raportti-ikkunan
	 * 
	 */
	public void openReportWindow(){
		reportWindow = new RaporttiGUI(this);
	}
	
	/**Haetaan tietokannasta käyttäjän kaikki tulot
	 * @return	Palauttaa desimaaliluvun kaikista käyttäjän tuloista
	 */
	public double collectIncome(){
		double income=0;
		try{
			//int user_id = database.getUserKey(account);
			//income = database.getIncome(user_id);
			int user_id = dbController.getUserKey(account);
			income = dbController.getEvent(user_id, true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return income;
	}
	/**Palauttaa tietokannasta käyttäjän kaikki menot
	 * @return	Palauttaa desimaaliluvun kaikista käyttäjän menoista
	 * TODO Yhdistä tämä funktio collectIncome funktion kanssa
	 */
	public double collectExpenses(){
		double income=0;
		try{
			//int user_id = database.getUserKey(account);
			//income = database.getExpenses(user_id);
			int user_id = dbController.getUserKey(account);
			income = dbController.getEvent(user_id, false);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return income;
	}
	
}


