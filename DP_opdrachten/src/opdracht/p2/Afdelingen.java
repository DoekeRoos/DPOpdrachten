package opdracht.p2;

import java.util.ArrayList;

public class Afdelingen {
	private int anr;
	private String naam;
	private String locatie;
	private Medewerkers afdelingshoofd;
	private ArrayList<Medewerkers> medewerkers = new ArrayList<Medewerkers>();
	
	public Afdelingen(int aR, String nM, String lE, Medewerkers afD){
		anr = aR;
		naam = nM;
		locatie = lE;
		setAfdelingshoofd(afD);
	}

	public ArrayList<Medewerkers> getMedewerkers() { return medewerkers; }

	public void setMedewerkers(ArrayList<Medewerkers> medewerkers) { this.medewerkers = medewerkers; }

	public Medewerkers getAfdelingshoofd() { return afdelingshoofd; }

	public void setAfdelingshoofd(Medewerkers afdelingshoofd) {	this.afdelingshoofd = afdelingshoofd; }

	public String getLocatie() { return locatie; }

	public void setLocatie(String locatie) { this.locatie = locatie; }

	public String getNaam() { return naam; }

	public void setNaam(String naam) { this.naam = naam; }

	public int getAnr() { return anr; }

	public void setAnr(int anr) { this.anr = anr; }
	
	@Override
	public String toString(){
		return String.format("anr = %d\nnaam = %s\nlocatie = %s\nhoofd = %s\n", getAnr(), getNaam(), getLocatie(), getAfdelingshoofd(), getNaam());
	}
}
