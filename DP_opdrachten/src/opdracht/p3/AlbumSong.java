package opdracht.p3;

public class AlbumSong {
	private Album _album;
	private Song _song;

	public AlbumSong(Album a, Song s) {
		setAlbum(a);
		setSong(s);
	}

	public Album getAlbum() { return _album; }

	public void setAlbum(Album _album) { this._album = _album; }

	public Song getSong() { return _song; }

	public void setSong(Song _song) { this._song = _song; }
}
