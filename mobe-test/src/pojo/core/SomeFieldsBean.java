package pojo.core;

import java.util.Calendar;
import java.util.Date;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;

@Entity
public class SomeFieldsBean {

	@Property
	private boolean bol1;
	private boolean bol2;

	@Property
	private int int1;
	private double dob1;

	@Property
	private String str1;
	private String str2;

	@Property
	private Calendar cal1;
	private Date dat1;

	public boolean isBol1() {
		return bol1;
	}

	public void setBol1(boolean bol1) {
		this.bol1 = bol1;
	}

	public boolean isBol2() {
		return bol2;
	}

	public void setBol2(boolean bol2) {
		this.bol2 = bol2;
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

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public Calendar getCal1() {
		return cal1;
	}

	public void setCal1(Calendar cal1) {
		this.cal1 = cal1;
	}

	public Date getDat1() {
		return dat1;
	}

	public void setDat1(Date dat1) {
		this.dat1 = dat1;
	}

}
