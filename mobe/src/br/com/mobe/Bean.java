package br.com.mobe;

import static br.com.mobe.StringUtils.formatted;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;

@Entity
public class Bean {

	@Property
	private boolean importAlbum = true;

	@Property
	private int songsNumber = 10;

	@Property
	private double artistBestScore = -1.5f;

	@Property
	private String albumTitle = "Highway to Hell";

	@Property
	private Calendar releaseYear = Calendar.getInstance();

	@Override
	public String toString() {
		return "Bean [importAlbum=" + importAlbum + ", songsNumber=" + songsNumber + ", artistBestScore=" + artistBestScore + ", albumTitle=" + albumTitle + ", releaseYear=" + formatted(releaseYear)
				+ "]";
	}

}
