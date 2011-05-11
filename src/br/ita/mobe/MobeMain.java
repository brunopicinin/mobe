package br.ita.mobe;

import android.os.Bundle;
import br.ita.mobe.annotation.View;
import br.ita.mobe.app.FormActivity;

public class MobeMain extends FormActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBean(new Bean1());
	}
}

@View
class Bean1 {

	private String firstName = "Bruno";
	private String surname;
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
