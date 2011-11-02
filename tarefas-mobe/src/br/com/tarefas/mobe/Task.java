package br.com.tarefas.mobe;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;
import br.com.mobe.orm.annotation.Id;

@Entity
public class Task {

	@Property
	@Id(autoIncrement = true)
	private Long id;

	@Property
	private String title;

	@Property
	private int importance;

	@Property
	private Calendar deadline;

	@Property
	private boolean done;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public Calendar getDeadline() {
		return deadline;
	}

	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return title;
	}

}
