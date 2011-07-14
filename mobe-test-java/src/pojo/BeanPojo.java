package pojo;

public class BeanPojo {

	public static Object noAnn() {
		return new NoAnnotation();
	}

	public static Object fieldAnn() {
		return new FieldAnnotation();
	}

	public static Object methodAnn() {
		return new MethodAnnotation();
	}

	public static Object classAnn() {
		return new ClassAnnotation();
	}

}
