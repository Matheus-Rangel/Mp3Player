package mp3player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.DefaultListModel;
/**
 * Gerencia o player, com uma lista de musicas que podem ser tocadas
 * @author Matheus Rangel de Melo
 *
 */
public class PlayerManager {
	public Playlist currentPlaylist;
	public Playlist allMusics;
	public DefaultListModel<Playlist> playlists;
	private Music currentMusic;
	private Thread t;
	/**
	 * Cria uma instancia do PlayerManager com as musicas encontradas no arquivos musicas.txt
	 * Caso não encontre o arquivo a instancia será criada sem nenhuma musica
	 * 
	 */
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
	/**
	 * Toca a musica atual
	 */
	public void play() {
		if(currentMusic == null) {
			currentMusic = currentPlaylist.getMusic();
		}
		if(t != null) {
			t.interrupt();
		}
		t = new Thread() {
			@Override
			public void run() {
				currentMusic.play();
			}
		};
		t.start();
	}
	/**
	 * Para de tocar a musica atual.
	 */
	public void stop() {
		if(t != null) {
			t.interrupt();
			currentMusic.close();
		}
	}
	/**
	 * Vai para a proxima musica.
	 */
	public void next() {
		t.interrupt();
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = currentPlaylist.nextMusic();
		this.play();
	}
	/**
	 * Volta a para a musica anterior
	 */
	public void prev() {
		t.interrupt();
		if(currentMusic != null)
			currentMusic.close();
		currentMusic = currentPlaylist.prevMusic();
		this.play();
	}
	/**
	 * Seleciona um playlist para tocar
	 * @param index indice da playlist para tocar
	 */
	public void setPlaylist(int index){
		this.currentPlaylist = this.playlists.elementAt(index);
		currentMusic = currentPlaylist.getMusic();
	}
	/**
	 * Seleciona uma musica da playlist atual para tocar
	 * @param index indice da musica na playlist atual
	 */
	public void setCurrentMusic(int index) {
		this.currentPlaylist.setIndex(index);
		this.currentMusic = this.currentPlaylist.getMusic();
		
	}
	/**
	 * Adiciona um nova musica ao PlayerManager
	 * @param file arquivo da musica a ser adicionado
	 */
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
			e.printStackTrace();
		}
	}
	/**
	 * Adiciona todas as musicas de um diretorio ao PlayerManager
	 * @param file Direto a ser adicionado
	 */
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
	/**
	 * 
	 * @return Nome da Musica atual.
	 */
	public String getMusicName() {
		if(currentMusic != null)
			return this.currentMusic.toString();
		else
			return "";
	}
}