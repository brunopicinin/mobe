package br.ita.mobe;

import static br.ita.mobe.StringUtils.formatted;

import java.util.Calendar;

import br.ita.mobe.annotation.Property;

@Property
public class Bean2 {

	private boolean importAlbum = true;
	private int songsNumber = 10;
	private float artistBestScore = -1.5f;
	// private int artistBestScore = 0;
	private String albumTitle = "Highway to Hell";
	private Calendar releaseYear = Calendar.getInstance();

	@Override
	public String toString() {
		return "Bean2 [importAlbum=" + importAlbum + ", songsNumber=" + songsNumber + ", artistBestScore=" + artistBestScore + ", albumTitle=" + albumTitle + ", releaseYear=" + formatted(releaseYear)
				+ "]";
	}

}
