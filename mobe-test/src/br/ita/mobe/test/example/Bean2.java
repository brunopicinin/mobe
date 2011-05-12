package br.ita.mobe.test.example;

import br.ita.mobe.annotation.View;

public class Bean2 {

	@View
	private String firstName = "Bruno";
	private String surname;
	@View
	private int myAge = 22;
	private boolean alive = true;

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public int getMyAge() {
		return myAge;
	}

	public boolean isAlive() {
		return alive;
	}

}
