package mp3player;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class Music {
	private File file;
	private Player player;

	public Music(File file) {
		this.file = file;
	}

	public void close() {
		if (player != null)
			player.close();
	}

	public void play() {
		try {
			FileInputStream fis = new FileInputStream(this.file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println("Problem playing file " + file.getPath());
			System.out.println(e);
		}
		try {
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


