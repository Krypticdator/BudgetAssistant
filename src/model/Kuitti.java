package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Kuitti {
	private String tunniste;
	private double summa;
	private boolean positiivinen;
	private ArrayList erittely;
	public Kuitti(boolean positiivinen, String tunniste, double summa){
		this.tunniste = tunniste;
		this.positiivinen=positiivinen;
		this.summa = summa;
		erittely = new ArrayList();
	}
	public void lisaaErittely(boolean positiivinen, String nimi, double summa){
		Kuitti meno = new Kuitti(positiivinen, nimi, summa);
		erittely.add(meno);
	}
	public double summaaErittely(){
		Iterator<Object> it = erittely.iterator();
		double summa=0;
		while(it.hasNext())
		{
		    Kuitti kuitti =(Kuitti) it.next();
		    summa = summa + kuitti.getSumma();
		    //Do something with obj
		}
		return summa;
			
	}
	public void poistaErottelu(String nimi){
		Iterator<Object> it = erittely.iterator();
		while(it.hasNext()){
			Kuitti kuitti = (Kuitti)it.next();
			if(kuitti.tunniste == nimi){
				it.remove();
			}
		}
	}
	public double getSumma(){return this.summa;}
}

