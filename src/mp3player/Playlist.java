package mp3player;

import javax.swing.DefaultListModel;
public class Playlist {
	private String name;
	private DefaultListModel<Music> musics;
	private int index;
	
	public Playlist(String name) {
		super();
		this.name = name;
		this.musics = new DefaultListModel<Music>();
		this.index = 0;
	}
	public Playlist(Playlist p){
		this.name = p.name;
		this.musics = p.musics;
		this.index = p.index;
	}
	
	public void removeMusic(int index) {
		this.musics.remove(index);
	}
	
	public void addMusic(Music m) {
		this.musics.addElement(m);
	}
	
	public Music getMusic() {
		return this.musics.get(index);
	}
	
	public Music nextMusic() {
		if(this.index < this.musics.size() - 1) {
			this.index ++;
			return this.musics.get(this.index);
		}else {
			this.index = 0;
			return this.musics.get(this.index);
		}
	}
	
	public Music prevMusic() {
		if(this.index == 0) {
			return this.musics.get(this.index);
		}else {
			this.index --;
			return this.musics.get(this.index);
		}
	}
	public void setIndex(int index) {
		if(index >= 0 && index < this.musics.size()) {
			this.index = index;
		}
	}
	public void Swap(int a, int b) {
		Music c = this.musics.getElementAt(a);
		this.musics.remove(a);
		this.musics.add(a, this.musics.get(b));
		this.musics.remove(b);
		this.musics.add(b, c);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public DefaultListModel<Music> getMusics() {
		return this.musics;
	}
	
	public boolean contains(Music m) {
		return musics.contains(m);
	}
	
	public void setName(String n) {
		this.name = n;
	}
}
