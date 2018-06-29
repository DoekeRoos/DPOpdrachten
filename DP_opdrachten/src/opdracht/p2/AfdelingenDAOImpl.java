package opdracht.p2;


import java.sql.ResultSet;
import java.util.ArrayList;

public class AfdelingenDAOImpl implements AfdelingenDAO{
	private SQLConnection sqlconn = null;
	private MedewerkersDAOImpl mdi = null;

	public AfdelingenDAOImpl(SQLConnection sC, MedewerkersDAOImpl mdImpl) {
		sqlconn = sC;
		mdi = mdImpl;
	}

	@Override
	public ArrayList<Afdelingen> GetAllAfdelingen() {
		ArrayList<Afdelingen> result = new ArrayList<Afdelingen>();
		try {
			sqlconn.setTable("AFDELINGEN");
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM %s", sqlconn.getTable()));
			while (rs.next()) {
				Afdelingen a = new Afdelingen(rs.getInt("ANR"), rs.getString("NAAM"), rs.getString("LOCATIE"),
						mdi.GetMedewerker(rs.getInt("HOOFD")));
				a.setMedewerkers(GetMedewerkersByAfdeling(a));
				result.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<Medewerkers> GetMedewerkersByAfdeling(Afdelingen a) {
		ArrayList<Medewerkers> result = new ArrayList<Medewerkers>();
		try {
			ResultSet rs = sqlconn.Retrieve(
					String.format("SELECT M.MNR FROM AFDELINGEN A, MEDEWERKERS M WHERE M.AFD = %d AND A.ANR = %d",
							a.getAnr(), a.getAnr()));
			while (rs.next()) {
				result.add(mdi.GetMedewerker(rs.getInt("MNR")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Afdelingen GetAfdeling(int id) {
		try {
			sqlconn.setTable("AFDELINGEN");
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM %s WHERE ANR = %d", sqlconn.getTable(), id));

			if (rs.next()) {
				return new Afdelingen(rs.getInt("ANR"), rs.getString("NAAM"), rs.getString("LOCATIE"),
						mdi.GetMedewerker(rs.getInt("HOOFD")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean AddAfdeling(Afdelingen a) {
		try {
			sqlconn.setTable("AFDELINGEN");
			if (sqlconn.Update(String.format(
					"INSERT INTO %s (ANR, NAAM, LOCATIE, HOOFD) VALUES (%d, '%s', '%s', (SELECT MNR FROM MEDEWERKERS WHERE MNR = %d))",
					sqlconn.getTable(), a.getAnr(), a.getNaam(), a.getLocatie(), a.getAfdelingshoofd().getMnr()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean UpdateAfdeling(Afdelingen a) {
		try {
			sqlconn.setTable("AFDELINGEN");
			if (sqlconn.Update(
					String.format("UPDATE %s SET ANR = %d, NAAM = '%s', LOCATIE = '%s', HOOFD = %d WHERE ANR = %d",
							sqlconn.getTable(), a.getAnr(), a.getNaam(), a.getLocatie(),
							a.getAfdelingshoofd().getMnr() != 0 ? a.getAfdelingshoofd().getMnr() : 0, a.getAnr()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteAfdeling(Afdelingen a) {
		try {
			sqlconn.setTable("AFDELINGEN");
			if (sqlconn.Update(String.format("DELETE FROM %s WHERE ANR = %d", sqlconn.getTable(), a.getAnr()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}

