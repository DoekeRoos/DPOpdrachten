package opdracht.p3;

import java.util.ArrayList;

public interface SongDAO {
	public ArrayList<Song> GetAllSongs();

	public Song GetSong(int id);

	public boolean AddSong(Song s);

	public boolean UpdateSong(Song s);

	public boolean DeleteSong(Song s);
}
