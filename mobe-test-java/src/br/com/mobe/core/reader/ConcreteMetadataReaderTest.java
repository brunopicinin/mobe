package br.com.mobe.core.reader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import pojo.FieldAnnotation;
import pojo.MethodAnnotation;
import pojo.NoAnnotation;
import pojo.core.NoFieldsBean;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.reader.ConcreteMetadataReader;
import br.com.mobe.core.reader.MetadataReader;

public class ConcreteMetadataReaderTest {

	private MetadataReader reader;

	@Before
	public void setUp() {
		reader = new ConcreteMetadataReader();
	}

	@Test
	public void createMetadataNoAnn() {
		ClassMetadata metadata = reader.createMetadata(NoAnnotation.class);
		List<Property> properties = metadata.getProperties();
		assertEquals(0, properties.size());
	}

	@Test
	public void createMetadataFieldAnn() {
		ClassMetadata metadata = reader.createMetadata(FieldAnnotation.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void createMetadataMethodAnn() {
		ClassMetadata metadata = reader.createMetadata(MethodAnnotation.class);
		assertCorrectMetadata(metadata);
	}

	@Test
	public void createMetadataClassAnn() {
		ClassMetadata metadata = reader.createMetadata(NoFieldsBean.class);
		assertCorrectMetadata(metadata);
	}

	private void assertCorrectMetadata(ClassMetadata metadata) {
		List<Property> properties = metadata.getProperties();
		assertEquals(4, properties.size());

		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		fields.put("firstName", String.class);
		fields.put("surname", String.class);
		fields.put("myAge", int.class);
		fields.put("alive", boolean.class);
		Set<String> keys = fields.keySet();

		for (Property property : properties) {
			String name = property.getName();
			Class<?> type = property.getType();
			assertTrue(keys.contains(name));
			assertEquals(fields.get(name), type);
		}
	}

}
