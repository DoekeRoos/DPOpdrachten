package opdracht.p3;

import java.util.ArrayList;

public interface AlbumDAO {
	public ArrayList<Album> GetAllAlbums();

	public Album GetAlbum(int id);

	public boolean AddAlbum(Album a);

	public boolean UpdateAlbum(Album a);

	public boolean DeleteAlbum(Album a);
}