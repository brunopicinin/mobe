package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class ClassMetadataTest {

	@Test
	public void testAddProperty() {
		PropertyDescriptor property = new PropertyDescriptor("name", String.class);
		ClassMetadata metadata = new ClassMetadata(Object.class);
		metadata.addProperty(property);
		assertEquals(1, metadata.getProperties().size());
		assertEquals(property, metadata.getProperties().get(0));
	}
}
