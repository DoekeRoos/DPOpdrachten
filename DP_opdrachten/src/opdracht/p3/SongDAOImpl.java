package opdracht.p3;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SongDAOImpl implements SongDAO{
	private SQLConnection sqlconn = null;

	public SongDAOImpl(SQLConnection sC) {
		sqlconn = sC;
	}

	@Override
	public ArrayList<Song> GetAllSongs() {
		ArrayList<Song> result = new ArrayList<Song>();
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM NUMMER"));
			while (rs.next()) {
				Song s = new Song(rs.getInt("ID"), rs.getString("TITEL"));
				result.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Song GetSong(int id) {
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM NUMMER WHERE ID = %d", id));

			if (rs.next()) {
				return new Song(rs.getInt("ID"), rs.getString("TITEL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean AddSong(Song s) {
		try {
			if (sqlconn.Update(String.format("INSERT INTO NUMMER (TITEL) VALUES ('%s')", s.getTitle()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateSong(Song s) {
		try {
			if (sqlconn.Update(
					String.format("UPDATE NUMMER SET ID = %d, TITEL = '%s' WHERE ID = %d", s.getId(), s.getTitle()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteSong(Song s) {
		try {
			if (sqlconn.Update(String.format("DELETE FROM NUMMER WHERE ID = %d", s.getId()))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

