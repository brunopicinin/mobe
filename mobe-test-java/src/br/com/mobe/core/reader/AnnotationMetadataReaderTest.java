package br.com.mobe.core.reader;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pojo.NoEntityBean;
import pojo.core.AllFieldsBean;
import pojo.core.CompleteBean;
import pojo.core.NoFieldsBean;
import pojo.core.SomeFieldsBean;
import pojo.orm.NotNullFieldBean;
import pojo.orm.UniqueFieldBean;
import pojo.orm.id.AutoIncrementIdBean;
import pojo.orm.id.DuplicatedIdBean;
import pojo.orm.id.LongIdBean;
import pojo.orm.id.StringIdBean;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;

public class AnnotationMetadataReaderTest {

	private MetadataReader reader;
	private Map<String, BeanField> fields;

	@Before
	public void setUp() {
		reader = new AnnotationMetadataReader();
		fields = new HashMap<String, BeanField>();
	}

	@After
	public void tearDown() {
		fields.clear();
	}

	/*
	 * Core
	 */

	@Test
	public void allFields() throws Exception {
		ClassMetadata metadata = reader.createMetadata(AllFieldsBean.class);
		add("bol1", boolean.class);
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void noFields() throws Exception {
		ClassMetadata metadata = reader.createMetadata(NoFieldsBean.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void someFields() throws Exception {
		ClassMetadata metadata = reader.createMetadata(SomeFieldsBean.class);
		add("bol1", boolean.class);
		add("int1", int.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void completeBean() throws Exception {
		ClassMetadata metadata = reader.createMetadata(CompleteBean.class);
		add("bol1", boolean.class);
		add("byt1", byte.class);
		add("sho1", short.class);
		add("int1", int.class);
		add("lon1", long.class);
		add("flo1", float.class);
		add("dob1", double.class);
		add("cha1", char.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		add("dat1", Date.class);
		assertCorrectMetadata(metadata);
	}

	@Test(expected = IllegalMetadataException.class)
	public void noEntity() throws Exception {
		reader.createMetadata(NoEntityBean.class);
	}

	/*
	 * ORM
	 */

	@Test
	public void longId() throws Exception {
		ClassMetadata metadata = reader.createMetadata(LongIdBean.class);
		BeanField id = add("id", long.class);
		id.isPrimaryKey = true;
		add("bol1", boolean.class);
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void autoIncrementId() throws Exception {
		ClassMetadata metadata = reader.createMetadata(AutoIncrementIdBean.class);
		BeanField id = add("id", long.class);
		id.isPrimaryKey = true;
		id.autoIncrement = true;
		add("bol1", boolean.class);
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void stringId() throws Exception {
		ClassMetadata metadata = reader.createMetadata(StringIdBean.class);
		BeanField id = add("strId", String.class);
		id.isPrimaryKey = true;
		add("bol1", boolean.class);
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test(expected = IllegalMetadataException.class)
	public void duplicatedId() throws Exception {
		reader.createMetadata(DuplicatedIdBean.class);
	}

	@Test
	public void notNullField() throws Exception {
		ClassMetadata metadata = reader.createMetadata(NotNullFieldBean.class);
		BeanField id = add("id", long.class);
		id.isPrimaryKey = true;
		id.autoIncrement = true;
		BeanField bol1 = add("bol1", boolean.class);
		bol1.notNull = true;
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void uniqueField() throws Exception {
		ClassMetadata metadata = reader.createMetadata(UniqueFieldBean.class);
		BeanField id = add("id", long.class);
		id.isPrimaryKey = true;
		id.autoIncrement = true;
		BeanField bol1 = add("bol1", boolean.class);
		bol1.unique = true;
		add("int1", int.class);
		add("dob1", double.class);
		add("str1", String.class);
		add("cal1", Calendar.class);
		assertCorrectMetadata(metadata);
	}

	/*
	 * Auxiliary methods and classes.
	 */

	private class BeanField {
		public String name;
		public Class<?> type;
		public boolean isPrimaryKey = false;
		public boolean autoIncrement = false;
		public boolean notNull = false;
		public boolean unique = false;

		public BeanField(String name, Class<?> type) {
			this.name = name;
			this.type = type;
		}
	}

	private BeanField add(String name, Class<?> type) {
		BeanField beanField = new BeanField(name, type);
		fields.put(name, beanField);
		return beanField;
	}

	private void assertCorrectMetadata(ClassMetadata metadata) {
		List<Property> properties = metadata.getProperties();
		assertEquals(fields.size(), properties.size());

		Set<String> keys = fields.keySet();
		for (Property property : properties) {
			String name = property.getName();
			assertTrue(keys.contains(name));

			BeanField field = fields.get(name);
			assertEquals(field.name, name);

			Class<?> type = property.getType();
			assertEquals(field.type, type);

			boolean primaryKey = property.isPrimaryKey();
			assertEquals(field.isPrimaryKey, primaryKey);
			if (primaryKey) {
				boolean autoIncrement = property.isAutoIncrement();
				assertEquals(field.autoIncrement, autoIncrement);
			}

			boolean notNull = property.isNotNull();
			assertEquals(field.notNull, notNull);

			boolean unique = property.isUnique();
			assertEquals(field.unique, unique);
		}
	}
}
