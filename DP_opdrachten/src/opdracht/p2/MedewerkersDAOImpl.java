package opdracht.p2;

import java.sql.ResultSet;

public class MedewerkersDAOImpl implements MedewerkersDAO {
	private SQLConnection sqlconn = null;

	public MedewerkersDAOImpl(SQLConnection sqlc) {
		sqlconn = sqlc;
	}
	
	@Override
	public Medewerkers GetMedewerker(int id) {
		try {
			sqlconn.setTable("MEDEWERKERS");
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM %s WHERE MNR = %d", sqlconn.getTable(), id));

			if (rs.next()) {
				return new Medewerkers(rs.getInt("MNR"), rs.getString("VOORL"), rs.getString("NAAM"),
						rs.getString("FUNCTIE"), rs.getInt("CHEF") != 0 ? GetMedewerker(rs.getInt("CHEF")) : null,
						rs.getString("GBDATUM"), rs.getInt("MAANDSAL"), rs.getInt("COMM"), rs.getInt("AFD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
