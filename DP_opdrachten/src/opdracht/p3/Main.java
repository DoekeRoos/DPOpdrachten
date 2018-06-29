package opdracht.p3;

import java.util.ArrayList;

public class Main {
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String DB_USER = "system";
	private static final String DB_PASS = "8fpd6y4h";
	private static SQLConnection sqlconn = null;

	public static void main(String[] args) {
		sqlconn = new SQLConnection(DB_DRIV, DB_URL, DB_USER, DB_PASS);
		SongDAOImpl sdi = new SongDAOImpl(sqlconn);
		AlbumDAOImpl adi = new AlbumDAOImpl(sqlconn);
		AlbumSongDAOImpl asdi = new AlbumSongDAOImpl(sqlconn, adi, sdi);
		adi.setAlbumSongDAOImpl(asdi);

		System.out.println("Alle namen van de albums waar de nummers op staan");
		ArrayList<Album> allAlbums = adi.GetAllAlbums();

		for (Album a : allAlbums) {
			for (Song s : a.getSongsOnAlbum()) {
				System.out.println("Nummer: " + s.getTitle());
				for (Album al : s.getFeatures()) {
					System.out.println("Album: " + al.getName());
				}
			}
		}

		System.out.println("\nNummer Toevoegen\n");
		Album a = new Album(0, "Alboaem", "25-FEB-17");
		Song song = new Song(0, "Noaemmaer");
		a.addSong(song);
		adi.AddAlbum(a);

		a = adi.GetAllAlbums().get(adi.GetAllAlbums().size() - 1);
		song = sdi.GetAllSongs().get(sdi.GetAllSongs().size() - 1);

		for (Song s : a.getSongsOnAlbum()) {
			System.out.println("Album: " + a.getName());
			System.out.println("Nummer: " + s.getTitle());
		}

		System.out.println("Nummer & Album Verwijderen\n");
		//asdi.DeleteAlbumSong(new AlbumSong(a, song));

		if (adi.GetAlbum(a.getId()) != null) {
			for (Song s : adi.GetAlbum(a.getId()).getSongsOnAlbum()) {
				System.out.println(s.getTitle());
			}
		} else {
			System.out.println("Album bestaat niet");
		}
		System.out.println("\n");
	}
}