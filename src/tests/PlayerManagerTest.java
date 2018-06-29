package tests;

import java.io.File;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mp3player.PlayerManager;
import mp3player.Playlist;
class PlayerManagerTest {
	private static PlayerManager p;
	private static Playlist pl;
	@BeforeAll
	static void begin() {
		p = new PlayerManager();
		p.addDirectory(new File("D:\\Musicas"));//Diretorio com Musicas mp3
		pl = new Playlist("Teste");
	}
	@Test
	void playTest() {
		p.play();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.next();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void playlistTest() {
		p.playlists.addElement(pl);
		pl.addMusic(p.allMusics.getMusic());
		p.allMusics.nextMusic();
		pl.addMusic(p.allMusics.getMusic());
		p.setPlaylist(0);
		p.play();
		assert(p.currentPlaylist.getMusics().size() == 2);
		pl.removeMusic(0);
		assert(p.currentPlaylist.getMusics().size() == 1);
	}
	
}
