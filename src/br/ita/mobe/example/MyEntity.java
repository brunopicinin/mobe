package br.ita.mobe.example;

public class MyEntity {

	private String firstName = "Bruno";
	private String surname;
	private Integer myAge = 10;
	private boolean alive = true;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getMyAge() {
		return myAge;
	}

	public void setMyAge(Integer age) {
		this.myAge = age;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
