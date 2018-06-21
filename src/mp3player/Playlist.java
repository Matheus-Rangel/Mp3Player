package mp3player;

import java.io.File;
import java.util.LinkedList;

public class Playlist {
	private String name;
	private LinkedList<File> musics;
	private int index;
	
	public Playlist(String name) {
		super();
		this.name = name;
		this.musics = new LinkedList<File>();
		this.index = 0;
	}
	
	public void removeMusic(int index) {
		this.musics.remove(index);
	}
	
	public void addMusic(File file) {
		this.musics.add(file);
	}
	
	public File GetMusic() {
		return this.musics.get(this.index);
	}
	
	public File nextMusic() {
		if(this.index < this.musics.size() - 1) {
			this.index ++;
			return this.musics.get(this.index);
		}else {
			this.index = 0;
			return this.musics.get(this.index);
		}
	}
	
	public File prevMusic() {
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
		File c = this.musics.get(a);
		this.musics.remove(a);
		this.musics.add(a, this.musics.get(b));
		this.musics.remove(b);
		this.musics.add(b, c);
	}
	
	public String getName() {
		return this.name;
	}
}
