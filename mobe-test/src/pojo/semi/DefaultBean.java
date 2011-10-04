package pojo.semi;

import java.util.Calendar;
import java.util.Date;

import br.com.mobe.core.annotation.Mobe;

public class DefaultBean {

	@Mobe
	private boolean bol1 = true;
	private boolean bol2 = false;

	@Mobe
	private int int1 = 10;
	private int int2 = 20;

	@Mobe
	private double dob1 = 1.1;
	private double dob2 = 2.2;

	@Mobe
	private char cha1 = 'a';
	private char cha2 = 'b';

	@Mobe
	private String str1 = "hello";
	private String str2 = "world";

	@Mobe
	private Calendar cal1 = Calendar.getInstance();
	private Calendar cal2 = yesterday();

	@Mobe
	private Date dat1 = new Date();
	private Date dat2 = yesterdayDate();

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

	public int getInt2() {
		return int2;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public double getDob1() {
		return dob1;
	}

	public void setDob1(double dob1) {
		this.dob1 = dob1;
	}

	public double getDob2() {
		return dob2;
	}

	public void setDob2(double dob2) {
		this.dob2 = dob2;
	}

	public char getCha1() {
		return cha1;
	}

	public void setCha1(char cha1) {
		this.cha1 = cha1;
	}

	public char getCha2() {
		return cha2;
	}

	public void setCha2(char cha2) {
		this.cha2 = cha2;
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

	public Calendar getCal2() {
		return cal2;
	}

	public void setCal2(Calendar cal2) {
		this.cal2 = cal2;
	}

	public Date getDat1() {
		return dat1;
	}

	public void setDat1(Date dat1) {
		this.dat1 = dat1;
	}

	public Date getDat2() {
		return dat2;
	}

	public void setDat2(Date dat2) {
		this.dat2 = dat2;
	}

	private static Calendar yesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar;
	}

	private static Date yesterdayDate() {
		return yesterday().getTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bol1 ? 1231 : 1237);
		result = prime * result + ((cal1 == null) ? 0 : cal1.hashCode());
		result = prime * result + cha1;
		result = prime * result + ((dat1 == null) ? 0 : dat1.hashCode());
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
		DefaultBean other = (DefaultBean) obj;
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
