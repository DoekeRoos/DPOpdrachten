package opdracht.p2;

public class Schalen {
	private int snr = 0;
	private int ondergrens = 0;
	private int bovengrens = 0;
	private int toelagen = 0;
	
	public Schalen(int s, int o, int b, int t){
		setSnr(s);
		setOndergrens(o);
		setBovengrens(b);
		setToelagen(t);
	}

	public int getSnr() { return snr; }

	public void setSnr(int snr) { this.snr = snr; }

	public int getOndergrens() { return ondergrens; }

	public void setOndergrens(int ondergrens) {	this.ondergrens = ondergrens; }

	public int getBovengrens() { return bovengrens; }

	public void setBovengrens(int bovengrens) {	this.bovengrens = bovengrens; }

	public int getToelagen() { return toelagen; }

	public void setToelagen(int toelagen) { this.toelagen = toelagen; }
	
	@Override
	public String toString()
	{
		return String.format("snr = %d\nondergrens = %d\nbovengrens = %d\ntoelage = %d\n", getSnr(), getOndergrens(), getBovengrens(), getToelagen());
	}
}

