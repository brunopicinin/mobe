package pojo.core;

import java.util.Calendar;
import java.util.Date;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.annotation.Property;

@Entity
public class CompleteBean {

	public static CompleteBean getExample() {
		CompleteBean bean = new CompleteBean();
		bean.bol1 = true;
		bean.byt1 = 2;
		bean.sho1 = 4;
		bean.int1 = 8;
		bean.lon1 = 16l;
		bean.flo1 = 1.1f;
		bean.dob1 = 2.2;
		bean.cha1 = 'w';
		bean.str1 = "Hello";
		bean.cal1 = Calendar.getInstance();
		bean.dat1 = new Date(1999, 5, 20, 10, 11, 12);
		return bean;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bol1 ? 1231 : 1237);
		result = prime * result + byt1;
		result = prime * result + ((cal1 == null) ? 0 : cal1.hashCode());
		result = prime * result + cha1;
		result = prime * result + ((dat1 == null) ? 0 : dat1.hashCode());
		long temp;
		temp = Double.doubleToLongBits(dob1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(flo1);
		result = prime * result + int1;
		result = prime * result + (int) (lon1 ^ (lon1 >>> 32));
		result = prime * result + sho1;
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
		CompleteBean other = (CompleteBean) obj;
		if (bol1 != other.bol1) {
			return false;
		}
		if (byt1 != other.byt1) {
			return false;
		}
		if (cal1 == null) {
			if (other.cal1 != null) {
				return false;
			}
		} else if (!cal1.equals(other.cal1)) {
			return false;
		}
		if (cha1 != other.cha1) {
			return false;
		}
		if (dat1 == null) {
			if (other.dat1 != null) {
				return false;
			}
		} else if (!dat1.equals(other.dat1)) {
			return false;
		}
		if (Double.doubleToLongBits(dob1) != Double.doubleToLongBits(other.dob1)) {
			return false;
		}
		if (Float.floatToIntBits(flo1) != Float.floatToIntBits(other.flo1)) {
			return false;
		}
		if (int1 != other.int1) {
			return false;
		}
		if (lon1 != other.lon1) {
			return false;
		}
		if (sho1 != other.sho1) {
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
