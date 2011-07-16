package br.ita.mobe.pattern.reader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import pojo.ClassAnnotation;
import pojo.FieldAnnotation;
import pojo.MethodAnnotation;
import pojo.NoAnnotation;
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
		Object noAnn = new NoAnnotation();
		BeanMetadata metadata = reader.createMetadata(noAnn.getClass());
		List<PropertyDescriptor> properties = metadata.getProperties();
		assertEquals(0, properties.size());
	}

	@Test
	public void createMetadataFieldAnn() {
		Object fieldAnn = new FieldAnnotation();
		BeanMetadata metadata = reader.createMetadata(fieldAnn.getClass());
		assertCorrectMetadata(metadata);
	}

	@Test
	public void createMetadataMethodAnn() {
		Object methodAnn = new MethodAnnotation();
		BeanMetadata metadata = reader.createMetadata(methodAnn.getClass());
		assertCorrectMetadata(metadata);
	}

	@Test
	public void createMetadataClassAnn() {
		Object classAnn = new ClassAnnotation();
		BeanMetadata metadata = reader.createMetadata(classAnn.getClass());
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
		Set<String> keys = fields.keySet();

		for (PropertyDescriptor property : properties) {
			String name = property.getName();
			Class<?> type = property.getType();
			assertTrue(keys.contains(name));
			assertEquals(fields.get(name), type);
		}
	}

}
