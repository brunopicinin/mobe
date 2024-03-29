package pojo.orm.id;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;
import br.com.mobe.orm.annotation.Id;

@Entity
public class DuplicatedIdBean {

	@Property
	@Id
	private long id1;

	@Property
	@Id
	private long id2;

	@Property
	private boolean bol1;

	@Property
	private int int1;

	@Property
	private double dob1;

	@Property
	private String str1;

	@Property
	private Calendar cal1;

	public long getId1() {
		return id1;
	}

	public void setId1(long id1) {
		this.id1 = id1;
	}

	public long getId2() {
		return id2;
	}

	public void setId2(long id2) {
		this.id2 = id2;
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
