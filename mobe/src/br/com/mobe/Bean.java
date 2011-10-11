package br.com.mobe;

import static br.com.mobe.StringUtils.formatted;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;
import br.com.mobe.orm.annotation.Id;

@Entity
public class Bean {

	@Id(autoIncrement = true)
	private long id;

	@Property
	private boolean importAlbum = true;

	@Property
	private int songsNumber = 10;

	@Property
	private float artistBestScore = -1.5f;

	@Property
	private String albumTitle = "Highway to Hell";

	@Property
	private Calendar releaseYear = Calendar.getInstance();

	@Override
	public String toString() {
		return "Bean2 [id=" + id + ", importAlbum=" + importAlbum + ", songsNumber=" + songsNumber + ", artistBestScore=" + artistBestScore + ", albumTitle=" + albumTitle + ", releaseYear="
				+ formatted(releaseYear) + "]";
	}

}
