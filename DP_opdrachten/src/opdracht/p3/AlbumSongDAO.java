package opdracht.p3;

import java.util.ArrayList;

public interface AlbumSongDAO {
	public ArrayList<AlbumSong> GetAllFeatures();

	public AlbumSong GetAlbumSong(int albumid, int nummerid);

	public boolean AddAlbumSong(AlbumSong a);

	public boolean UpdateAlbumSong(AlbumSong a);

	public boolean DeleteAlbumSong(AlbumSong a);

	public boolean AddAllSongsForAlbum(Album a);
}

