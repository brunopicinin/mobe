package pojo.orm;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;
import br.com.mobe.orm.annotation.Id;
import br.com.mobe.orm.annotation.Unique;

@Entity
public class UniqueFieldBean {

	@Property
	@Id(autoIncrement = true)
	private long id;

	@Property
	private boolean bol1;

	@Property
	@Unique
	private int int1;

	@Property
	private double dob1;

	@Property
	private String str1;

	@Property
	private Calendar cal1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isBol1() {
		return bol1;
	}

	public void setBol1(boolean bol1) {
		this.bol1 = bol1;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public double getDob1() {
		return dob1;
	}

	public void setDob1(double dob1) {
		this.dob1 = dob1;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public Calendar getCal1() {
		return cal1;
	}

	public void setCal1(Calendar cal1) {
		this.cal1 = cal1;
	}

}
