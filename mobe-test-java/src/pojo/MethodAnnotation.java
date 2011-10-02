package pojo;

import br.com.mobe.annotation.Property;

public class MethodAnnotation {

	private String firstName = "Bruno";
	private String surname;
	private int myAge = 22;
	private boolean alive = true;

	@Property
	public String getFirstName() {
		return firstName;
	}

	@Property
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Property
	public String getSurname() {
		return surname;
	}

	@Property
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Property
	public int getMyAge() {
		return myAge;
	}

	@Property
	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	@Property
	public boolean isAlive() {
		return alive;
	}

	@Property
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
