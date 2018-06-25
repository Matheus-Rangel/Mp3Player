package mp3player;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;


public class Music {
	private File file;
	public AdvancedPlayer player;

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
			player = new AdvancedPlayer(bis);
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
	@Override
	public String toString() {
		return  this.file.getName().substring(0, this.file.getName().lastIndexOf('.')) ;
	}

	public boolean equals(Object obj) {
		try {
			return toString().equals(((Music)obj).toString());
		} catch (ClassCastException e) {
			return false;
		}
	}
}


