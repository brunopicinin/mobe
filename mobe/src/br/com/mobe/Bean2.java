package br.com.mobe;

import static br.com.mobe.StringUtils.formatted;

import java.util.Calendar;

import br.com.mobe.core.annotation.Mobe;
import br.com.mobe.orm.annotation.PrimaryKey;

@Mobe
public class Bean2 {

	@PrimaryKey
	private long pk;
	private boolean importAlbum = true;
	private int songsNumber = 10;
	private float artistBestScore = -1.5f;
	private String albumTitle = "Highway to Hell";
	private Calendar releaseYear = Calendar.getInstance();

	public Bean2() {
	}

	public Bean2(long pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "Bean2 [pk=" + pk + ", importAlbum=" + importAlbum + ", songsNumber=" + songsNumber + ", artistBestScore=" + artistBestScore + ", albumTitle=" + albumTitle + ", releaseYear="
				+ formatted(releaseYear) + "]";
	}

}
