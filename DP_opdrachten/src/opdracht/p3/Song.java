package opdracht.p3;

import java.util.ArrayList;

public class Song {
	private int _id;
	private String _title;
	private ArrayList<Album> _features = new ArrayList<Album>();

	public Song(int id, String title) {
		setId(id);
		setTitle(title);
	}

	public int getId() { return _id; }

	public void setId(int _id) { this._id = _id; }

	public String getTitle() { return _title; }

	public void setTitle(String _title) { this._title = _title; }

	public ArrayList<Album> getFeatures() { return _features; }

	public void setFeatures(ArrayList<Album> _features) { this._features = _features; }

	public void addFeatures(Album a) { _features.add(a); }
}
