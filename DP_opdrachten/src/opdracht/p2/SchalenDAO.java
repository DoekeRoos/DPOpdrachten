package opdracht.p2;

import java.util.ArrayList;

public interface SchalenDAO {
	public ArrayList<Schalen> GetAllSchalen();
	
	public Schalen GetSchaal(int id);
	
	public boolean AddSchaal(Schalen s);
	
	public boolean UpdateSchaal(Schalen s);
	
	public boolean DeleteSchaal(Schalen s);	
}
