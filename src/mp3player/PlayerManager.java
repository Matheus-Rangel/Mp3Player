package mp3player;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;


public class PlayerManager {
	private Playlist currentPlaylist;
	private LinkedList<Playlist> playlists;
	private Music currentMusic;
	
	
	public PlayerManager() {
		this.playlists = new LinkedList<Playlist>();
		this.playlists.add(new Playlist("Todas as Musicas"));
		this.currentPlaylist = this.playlists.get(0);
		//TODO SEARCH IN DIRECTORY.TXT FOR SAVED MUSICS.
		
		this.currentMusic = null;
	}
	public void play() {
		if(currentMusic == null) {
			currentMusic = new Music(currentPlaylist.GetMusic());
		}
		Thread t = new Thread() {
			@Override
			public void run() {
				currentMusic.play();
				next();
			}
		};
		t.start();
		
	}
	public void stop() {
		currentMusic.close();
	}
	public void next() {
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = new Music(currentPlaylist.nextMusic());
		this.play();
	}
	public void prev() {
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = new Music(currentPlaylist.prevMusic());
		this.play();
	}
	public void setPlaylist(int index){
		this.currentPlaylist = this.playlists.get(index);
		currentMusic = new Music(currentPlaylist.GetMusic());
	}
	public void setCurrentMusic(int index) {
		this.currentPlaylist.setIndex(index);
		this.currentMusic = new Music(this.currentPlaylist.GetMusic());
		
	}
	public void addMusic(File file) {
		this.playlists.get(0).addMusic(file);
	}
	public void addDirectory(File file) {
		if(file.isDirectory()) {
			File[] matchingFiles = file.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.endsWith("mp3");
			    }
			});
			for (int i = 0; i < matchingFiles.length; i++) {
				this.playlists.get(0).addMusic(matchingFiles[i]);
			}
		}else {
			System.err.println("addDirectory: Caminho especificado não é um diretorio");
		}
	}
}