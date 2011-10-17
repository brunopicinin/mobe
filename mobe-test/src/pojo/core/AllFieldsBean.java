package pojo.core;

import java.util.Calendar;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;

@Entity
public class AllFieldsBean {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bol1 ? 1231 : 1237);
		result = prime * result + ((cal1 == null) ? 0 : cal1.hashCode());
		long temp;
		temp = Double.doubleToLongBits(dob1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + int1;
		result = prime * result + ((str1 == null) ? 0 : str1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AllFieldsBean other = (AllFieldsBean) obj;
		if (bol1 != other.bol1) {
			return false;
		}
		if (cal1 == null) {
			if (other.cal1 != null) {
				return false;
			}
		} else if (!cal1.equals(other.cal1)) {
			return false;
		}
		if (Double.doubleToLongBits(dob1) != Double.doubleToLongBits(other.dob1)) {
			return false;
		}
		if (int1 != other.int1) {
			return false;
		}
		if (str1 == null) {
			if (other.str1 != null) {
				return false;
			}
		} else if (!str1.equals(other.str1)) {
			return false;
		}
		return true;
	}

}
