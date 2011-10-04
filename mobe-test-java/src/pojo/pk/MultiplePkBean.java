package pojo.pk;

import br.com.mobe.core.annotation.Mobe;
import br.com.mobe.orm.annotation.PrimaryKey;

@SuppressWarnings("unused")
@Mobe
public class MultiplePkBean {

	@PrimaryKey
	private int pInt;

	@PrimaryKey
	private String pString;

	private boolean bol1;
	private int int1;
	private float flo1;
	private String str1;

}
