package pojo;

import br.ita.mobe.annotation.View;

public class MethodAnnotation {

	private String firstName = "Bruno";
	private String surname;
	private int myAge = 22;
	private boolean alive = true;

	@View
	public String getFirstName() {
		return firstName;
	}

	@View
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@View
	public String getSurname() {
		return surname;
	}

	@View
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@View
	public int getMyAge() {
		return myAge;
	}

	@View
	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	@View
	public boolean isAlive() {
		return alive;
	}

	@View
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
