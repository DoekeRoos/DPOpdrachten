package opdracht.p3;

import java.sql.ResultSet;
import java.util.ArrayList;


public class AlbumDAOImpl implements AlbumDAO {
	private SQLConnection sqlconn = null;
	private AlbumSongDAOImpl asdi = null;

	public AlbumDAOImpl(SQLConnection sC) {
		sqlconn = sC;
	}

	public void setAlbumSongDAOImpl(AlbumSongDAOImpl a) {
		asdi = a;
	}

	@Override
	public ArrayList<Album> GetAllAlbums() {
		ArrayList<Album> result = new ArrayList<Album>();
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM ALBUM"));
			while (rs.next()) {
				Album a = new Album(rs.getInt("ID"), rs.getString("NAAM"), rs.getString("UITGEEF_DATUM"));
				asdi.AddAllSongsForAlbum(a);
				result.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Album GetAlbum(int id) {
		try {
			ResultSet rs = sqlconn.Retrieve(String.format("SELECT * FROM ALBUM WHERE ID = %d", id));

			if (rs.next()) {
				Album a = new Album(rs.getInt("ID"), rs.getString("NAAM"), rs.getString("UITGEEF_DATUM"));
				asdi.AddAllSongsForAlbum(a);
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean AddAlbum(Album a) {
		try {
			if (sqlconn.Update(String.format("INSERT INTO ALBUM (NAAM, UITGEEF_DATUM) VALUES ('%s', TO_DATE('%s'))",
					a.getName(), a.getReleaseDate()))) {
				Album album = GetAllAlbums().get(GetAllAlbums().size() - 1);
				for (Song s : a.getSongsOnAlbum()) {
					asdi.AddAlbumSong(new AlbumSong(album, s));
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateAlbum(Album a) {
		try {
			if (sqlconn.Update(
					String.format("UPDATE ALBUM SET ID = %d, NAAM = '%s', UITGEEF_DATUM = TO_DATE('%s') WHERE ID = %d",
							a.getId(), a.getName(), a.getReleaseDate(), a.getId()))) {
				for (Song s : a.getSongsOnAlbum()) {
					asdi.UpdateAlbumSong(new AlbumSong(a, s));
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteAlbum(Album a) {
		try {
			if (sqlconn.Update(String.format("DELETE FROM ALBUM WHERE ID = %d", a.getId()))) {
				for (Song s : a.getSongsOnAlbum()) {
					asdi.DeleteAlbumSong(new AlbumSong(a, s));
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
