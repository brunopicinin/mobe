package pojo.core;

import java.util.Calendar;
import java.util.Date;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;

@Entity
public class CompleteBean {

	@Property
	private boolean bol1;
	@Property
	private byte byt1;
	@Property
	private short sho1;
	@Property
	private int int1;
	@Property
	private long lon1;
	@Property
	private float flo1;
	@Property
	private double dob1;
	@Property
	private char cha1;
	@Property
	private String str1;
	@Property
	private Calendar cal1;
	@Property
	private Date dat1;

	public boolean isBol1() {
		return bol1;
	}

	public void setBol1(boolean bol1) {
		this.bol1 = bol1;
	}

	public byte getByt1() {
		return byt1;
	}

	public void setByt1(byte byt1) {
		this.byt1 = byt1;
	}

	public short getSho1() {
		return sho1;
	}

	public void setSho1(short sho1) {
		this.sho1 = sho1;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public long getLon1() {
		return lon1;
	}

	public void setLon1(long lon1) {
		this.lon1 = lon1;
	}

	public float getFlo1() {
		return flo1;
	}

	public void setFlo1(float flo1) {
		this.flo1 = flo1;
	}

	public double getDob1() {
		return dob1;
	}

	public void setDob1(double dob1) {
		this.dob1 = dob1;
	}

	public char getCha1() {
		return cha1;
	}

	public void setCha1(char cha1) {
		this.cha1 = cha1;
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

	public Date getDat1() {
		return dat1;
	}

	public void setDat1(Date dat1) {
		this.dat1 = dat1;
	}

}
