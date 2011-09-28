package br.ita.mobe;

import java.util.Calendar;

import br.ita.mobe.annotation.Property;

@SuppressWarnings("unused")
@Property
public class Bean2 {

	private boolean importAlbum = true;
	private int songsNumber = 10;
	private float artistBestScore = -1.5f;
	// private int artistBestScore = 0;
	private String albumTitle = "Highway to Hell";
	private Calendar releaseYear = Calendar.getInstance();

}
