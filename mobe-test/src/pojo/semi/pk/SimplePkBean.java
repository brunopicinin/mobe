package pojo.semi.pk;

import java.util.Calendar;

import br.com.mobe.core.annotation.Mobe;
import br.com.mobe.orm.annotation.PrimaryKey;

@Mobe
public class SimplePkBean {

	@PrimaryKey
	private long pk;

	private boolean bol1 = true;
	private int int1 = 10;
	private double dob1 = 1.1;
	private String str1 = "hello";
	private Calendar cal1 = Calendar.getInstance();

	public SimplePkBean() {
	}

	public SimplePkBean(long pk) {
		this.pk = pk;
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
		result = prime * result + (int) (pk ^ (pk >>> 32));
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
		SimplePkBean other = (SimplePkBean) obj;
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
		if (pk != other.pk) {
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
