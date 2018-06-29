package opdracht.p3;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AlbumSongDAOImpl implements AlbumSongDAO {
	private SQLConnection sqlconn = null;
	private AlbumDAOImpl adi = null;
	private SongDAOImpl sdi = null;

	public AlbumSongDAOImpl(SQLConnection sC, AlbumDAOImpl a, SongDAOImpl s) {
		sqlconn = sC;
		adi = a;
		sdi = s;
	}

	@Override
	public ArrayList<AlbumSong> GetAllFeatures() {
		ArrayList<AlbumSong> result = new ArrayList<AlbumSong>();
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM NUMMER"));
			while (rs.next()) {
				result.add(new AlbumSong(adi.GetAlbum(rs.getInt("ALBUM_ID")), sdi.GetSong(rs.getInt("NUMMER_ID"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public AlbumSong GetAlbumSong(int albumid, int nummerid) {
		try {
			ResultSet rs = sqlconn.Retrieve(String
					.format("SELECT * FROM ALBUM_NUMMER WHERE ALBUM_ID = %d AND NUMMER_ID = %d", albumid, nummerid));
			
			if (rs.next()) {
				return new AlbumSong(adi.GetAlbum(rs.getInt("ALBUM_ID")), sdi.GetSong(rs.getInt("NUMMER_ID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean AddAlbumSong(AlbumSong a) {
		try {
			if (sdi.AddSong(a.getSong())) {
				a.setSong(sdi.GetAllSongs().get(sdi.GetAllSongs().size() - 1));
				sqlconn.save();

				if (sqlconn.Update(String.format(
						"INSERT INTO ALBUM_NUMMER (ALBUM_ID, NUMMER_ID) VALUES ((SELECT ID FROM ALBUM WHERE ID = %d), (SELECT ID FROM NUMMER WHERE ID = %d))",
						a.getAlbum().getId(), a.getSong().getId()))) {
					return true;
				}
			} else {
				System.out.println("Add song failed");
				return false;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean UpdateAlbumSong(AlbumSong a) {
		try {
			if (GetAlbumSong(a.getAlbum().getId(), a.getSong().getId()) != null) {
				if (sqlconn.Update(String.format(
						"UPDATE ALBUM_NUMMER SET ALBUM_ID = %d, NUMMER_ID = %d WHERE ALBUM_ID = %d AND NUMMER_ID = %d",
						a.getAlbum().getId(), a.getSong().getId(), a.getAlbum().getId(), a.getSong().getId()))) {
					return true;
				}
			} else {
				AddAlbumSong(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteAlbumSong(AlbumSong a) {
		try {
			if (sqlconn.Update(String.format("DELETE FROM ALBUM_NUMMER WHERE ALBUM_ID = %d AND NUMMER_ID = %d",
					a.getAlbum().getId(), a.getSong().getId()))) {
				sdi.DeleteSong(a.getSong());
				adi.DeleteAlbum(a.getAlbum());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddAllSongsForAlbum(Album a) {
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM ALBUM_NUMMER WHERE ALBUM_ID = %d", a.getId()));

			while (rs.next()) {
				a.addSong(sdi.GetSong(rs.getInt("NUMMER_ID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
