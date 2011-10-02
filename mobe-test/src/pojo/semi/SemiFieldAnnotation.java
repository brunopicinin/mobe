package pojo.semi;

import br.com.mobe.core.annotation.Mobe;

public class SemiFieldAnnotation {

	@Mobe
	private String firstName = "Bruno";

	private String surname;

	@Mobe
	private int myAge = 22;

	private boolean alive = true;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getMyAge() {
		return myAge;
	}

	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
