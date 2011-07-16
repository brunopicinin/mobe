package br.ita.mobe.pattern.reader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import pojo.BeanPojo;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;

public class ConcreteMetadataReaderTest {

	private MetadataReader reader;

	@Before
	public void setUp() {
		reader = new ConcreteMetadataReader();
	}

	@Test
	public void createMetadataNoAnn() {
		Object noAnn = BeanPojo.noAnn();
		BeanMetadata metadata = reader.createMetadata(noAnn.getClass());
		List<PropertyDescriptor> properties = metadata.getProperties();
		assertEquals(0, properties.size());
	}

	@Test
	public void createMetadataFieldAnn() {
		Object fieldAnn = BeanPojo.fieldAnn();
		BeanMetadata metadata = reader.createMetadata(fieldAnn.getClass());
		assertCorrectMetadata(metadata);
	}

	private void assertCorrectMetadata(BeanMetadata metadata) {
		List<PropertyDescriptor> properties = metadata.getProperties();
		assertEquals(4, properties.size());

		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		fields.put("firstName", String.class);
		fields.put("surname", String.class);
		fields.put("myAge", int.class);
		fields.put("alive", boolean.class);

		for (PropertyDescriptor property : properties) {
			String name = property.getName();
			Class<?> type = property.getType();
			Set<String> keys = fields.keySet();
			assertTrue(keys.contains(name));
			assertEquals(fields.get(name), type);
		}
	}

	@Test
	public void createMetadataMethodAnn() {
		Object methodAnn = BeanPojo.methodAnn();
		BeanMetadata metadata = reader.createMetadata(methodAnn.getClass());
		assertCorrectMetadata(metadata);
	}

	@Test
	public void createMetadataClassAnn() {
		Object classAnn = BeanPojo.classAnn();
		BeanMetadata metadata = reader.createMetadata(classAnn.getClass());
		assertCorrectMetadata(metadata);
	}

}
