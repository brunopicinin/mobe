package pojo;

import br.com.mobe.core.annotation.Mobe;

public class MethodAnnotation {

	private String firstName = "Bruno";
	private String surname;
	private int myAge = 22;
	private boolean alive = true;

	@Mobe
	public String getFirstName() {
		return firstName;
	}

	@Mobe
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Mobe
	public String getSurname() {
		return surname;
	}

	@Mobe
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Mobe
	public int getMyAge() {
		return myAge;
	}

	@Mobe
	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	@Mobe
	public boolean isAlive() {
		return alive;
	}

	@Mobe
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
