package opdracht.p2;

import java.util.ArrayList;

public interface AfdelingenDAO {
	public ArrayList<Afdelingen> GetAllAfdelingen();
	
	public ArrayList<Medewerkers> GetMedewerkersByAfdeling(Afdelingen a);
	
	public Afdelingen GetAfdeling(int id);
	
	public boolean AddAfdeling(Afdelingen a);
	
	public boolean UpdateAfdeling(Afdelingen a);
	
	public boolean DeleteAfdeling(Afdelingen a);
}

