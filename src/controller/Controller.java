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

/**Ohjelman p��kontrolleri, joka toimii siltana SQL-kontrollerin, mallin (model) ja k�ytt�liittym�n v�lill�
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
	/**Avaa p��valikon k�ytt�j�n kirjautuessa onnistuneesti
	 * @param account	K�ytt�j�n sy�tt�m� k�ytt�j�tili
	 * @param password	K�ytt�j�n antama salasana
	 * @return			Palauttaa tosi, jos k�ytt�j�nimi + salasana on oikein
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
				System.out.println("Yritettiin luoda uutta db:t�");
				e.printStackTrace();
			}
		 }
	
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccessfulLogin;
	}
	/**K��refunktio k�ytt�liittym�lle
	 * Tallentaa tapahtuman tietokantaan
	 * @param tunniste	Tapahtuman tunniste, jolla sit� voidaan my�hemmin etsi�
	 * @param summa		Tapahtuman raham��r� euroina
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
	/**Tuhoaa kaikki k�ytt�j�n tapahtumat
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
	/**Rekister�i uuden k�ytt�j�n ohjelmalle tietokantaan
	 * @param user		Uusi k�ytt�j�nimi
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
	/**Palauttaa nykyisen k�ytt�j�nimen
	 * @return	K�ytt�j�nimi
	 */
	public String getAccount() {
		return account;
	}
	/**Tallentaa onnistuneesti kirjautuneen k�ytt�j�n nimen ohjelman ajon ajaksi
	 * @param account	T�m�n hetkinen k�ytt�j�
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
	
	/**Haetaan tietokannasta k�ytt�j�n kaikki tulot
	 * @return	Palauttaa desimaaliluvun kaikista k�ytt�j�n tuloista
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
	/**Palauttaa tietokannasta k�ytt�j�n kaikki menot
	 * @return	Palauttaa desimaaliluvun kaikista k�ytt�j�n menoista
	 * TODO Yhdist� t�m� funktio collectIncome funktion kanssa
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


