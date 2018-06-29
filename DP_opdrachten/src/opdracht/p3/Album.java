package opdracht.p3;

import java.util.ArrayList;

public class Album {
	private int _id;
	private String _name;
	private String _releaseDate;
	private ArrayList<Song> _songsOnAlbum = new ArrayList<Song>();

	public Album(int id, String name, String releaseDate) {
		setId(id);
		setName(name);
		setReleaseDate(releaseDate);
	}

	public int getId() { return _id; }

	public void setId(int _id) { this._id = _id; }

	public String getName() { return _name; }

	public void setName(String _name) {	this._name = _name; }

	public String getReleaseDate() { return _releaseDate; }

	public void setReleaseDate(String _releaseDate) { this._releaseDate = _releaseDate; }

	public ArrayList<Song> getSongsOnAlbum() { return _songsOnAlbum; }

	public void setSongsOnAlbum(ArrayList<Song> _songsOnAlbum) { this._songsOnAlbum = _songsOnAlbum; }

	public void addSong(Song s) {
		this._songsOnAlbum.add(s);
		s.addFeatures(this);
	}

	public void removeSong(Song s) {
		this._songsOnAlbum.remove(s);
		s.getFeatures().remove(s);
	}
}
