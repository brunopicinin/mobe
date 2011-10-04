package pojo;

import java.util.Calendar;
import java.util.Date;

import br.com.mobe.core.annotation.Mobe;

@SuppressWarnings("unused")
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

	private static Calendar yesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar;
	}

	private static Date yesterdayDate() {
		return yesterday().getTime();
	}

}
