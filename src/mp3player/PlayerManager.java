package mp3player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import javazoom.jl.player.advanced.PlaybackListener;

public class PlayerManager {
	public Playlist currentPlaylist;
	public Playlist allMusics;
	public DefaultListModel<Playlist> playlists;
	private Music currentMusic;
	private Thread t;
	
	public PlayerManager() {
		this.playlists = new DefaultListModel<Playlist>();
		this.allMusics = new Playlist("Todas as Musicas");
		this.currentPlaylist = new Playlist(allMusics);
		File f = new File("musicas.txt");
		if(f.exists()) {
			FileReader fr;
			try {
				fr = new FileReader(f);
				BufferedReader bf = new BufferedReader(fr);
				while(bf.ready()) {
					File mf = new File(bf.readLine());
					Music m = new Music(mf);
					allMusics.addMusic(m);
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.currentMusic = null;
	}
	public void play() {
		if(currentMusic == null) {
			currentMusic = currentPlaylist.GetMusic();
		}
		t = new Thread() {
			@Override
			public void run() {
				currentMusic.play();
			}
		};
		t.start();
	}
	
	public void stop() {
		if(t != null) {
			t.interrupt();
			currentMusic.close();
		}
	}
	public void next() {
		t.interrupt();
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = currentPlaylist.nextMusic();
		this.play();
	}
	public void prev() {
		t.interrupt();
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = currentPlaylist.prevMusic();
		this.play();
	}
	
	public void setPlaylist(int index){
		this.currentPlaylist = this.playlists.elementAt(index);
		currentMusic = currentPlaylist.GetMusic();
	}
	
	public void setCurrentMusic(int index) {
		this.currentPlaylist.setIndex(index);
		this.currentMusic = this.currentPlaylist.GetMusic();
		
	}
	public void addMusic(File file) {
		Music m = new Music(file);
		if(allMusics.contains(m)) {
			System.err.println("AddMusic: Musica " + m.toString() + " ja adicionada");
			return;
		}
		allMusics.addMusic(m);
		File f = new File("musicas.txt");
		try {
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (file.getName().endsWith(".mp3")){
				bw.write(file.getAbsolutePath());
				bw.newLine();
				bw.close();
			}else
				System.err.println("addMusic: Formato de arquivo não reconhecido");
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void addDirectory(File file) {
		if(file.isDirectory()) {
			File[] matchingFiles = file.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.endsWith("mp3");
			    }
			});
			for (int i = 0; i < matchingFiles.length; i++) {
				addMusic(matchingFiles[i]);
			}
		}else {
			System.err.println("addDirectory: Caminho especificado não é um diretorio");
		}
	}
	public String getMusicName() {
		if(currentMusic != null)
			return this.currentMusic.toString();
		else
			return "";
	}
}