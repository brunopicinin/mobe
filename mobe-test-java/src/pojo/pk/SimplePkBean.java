package pojo.pk;

import br.com.mobe.core.annotation.Mobe;
import br.com.mobe.orm.annotation.PrimaryKey;

@SuppressWarnings("unused")
@Mobe
public class SimplePkBean {

	@PrimaryKey
	private long pk;

	private boolean bol1;
	private int int1;
	private float flo1;
	private String str1;

}
