package opdracht.p2;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SchalenDAOImpl implements SchalenDAO {
	private SQLConnection sqlconn = null;

	public SchalenDAOImpl(SQLConnection sC) {
		sqlconn = sC;
	}

	@Override
	public ArrayList<Schalen> GetAllSchalen() {
		ArrayList<Schalen> result = new ArrayList<Schalen>();
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM %s", sqlconn.getTable()));
			while (rs.next()) {
				result.add(new Schalen(rs.getInt("SNR"), rs.getInt("ONDERGRENS"), rs.getInt("BOVENGRENS"),
						rs.getInt("TOELAGE")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Schalen GetSchaal(int id) {
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM %s WHERE SNR = %d", sqlconn.getTable(), id));

			if (rs.next()) {
				return new Schalen(rs.getInt("SNR"), rs.getInt("ONDERGRENS"), rs.getInt("BOVENGRENS"),
						rs.getInt("TOELAGE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean UpdateSchaal(Schalen s) {
		try {
			if (sqlconn.Update(String.format(
					"UPDATE %s SET SNR = %d, ONDERGRENS = %d, BOVENGRENS = %d, TOELAGE = %d WHERE SNR = %d",
					sqlconn.getTable(), s.getSnr(), s.getOndergrens(), s.getBovengrens(), s.getToelagen(), s.getSnr()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteSchaal(Schalen s) {
		try {
			if (sqlconn.Update(String.format("DELETE FROM %s WHERE SNR = %d", sqlconn.getTable(), s.getSnr()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddSchaal(Schalen s) {
		try {
			if (sqlconn.Update(
					String.format("INSERT INTO %s (SNR, ONDERGRENS, BOVENGRENS, TOELAGE) VALUES (%d, %d, %d, %d)",
							sqlconn.getTable(), s.getSnr(), s.getOndergrens(), s.getBovengrens(), s.getToelagen()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}
