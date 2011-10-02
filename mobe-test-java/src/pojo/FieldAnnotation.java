package pojo;

import br.com.mobe.annotation.Property;

public class FieldAnnotation {

	@Property
	private String firstName = "Bruno";
	@Property
	private String surname;
	@Property
	private int myAge = 22;
	@Property
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
