package opdracht.p2;

public class Medewerkers {
	private int mnr;
	private String voorletter;
	private String naam;
	private String functie;
	private Medewerkers chef;
	private String date;
	private int maandsalaris;
	private int comm;
	private int afd;
	
	public Medewerkers(int mR, String vL, String nM, String fC, Medewerkers m, String dT, int mS, int cM, int aD){
		setMnr(mR);
		setVoorletter(vL);
		setNaam(nM);
		setFunctie(fC);
		setChef(m);
		setDate(dT);
		setMaandsalaris(mS);
		setComm(cM);
		setAfd(aD);
	}

	public int getMnr() { return mnr; }

	public void setMnr(int mnr) { this.mnr = mnr; }

	public String getVoorletter() { return voorletter; }

	public void setVoorletter(String voorletter) { this.voorletter = voorletter; }

	public String getNaam() { return naam; }

	public void setNaam(String naam) { this.naam = naam; }

	public String getFunctie() { return functie; }

	public void setFunctie(String functie) { this.functie = functie; }

	public Medewerkers getChef() { return chef; }

	public void setChef(Medewerkers chef) { this.chef = chef; }

	public String getDate() { return date; }

	public void setDate(String date) { this.date = date; }

	public int getMaandsalaris() { return maandsalaris; }

	public void setMaandsalaris(int maandsalaris) { this.maandsalaris = maandsalaris; }

	public int getComm() { return comm; }

	public void setComm(int comm) {	this.comm = comm; }

	public int getAfd() { return afd; }

	public void setAfd(int afd) { this.afd = afd;}
}
