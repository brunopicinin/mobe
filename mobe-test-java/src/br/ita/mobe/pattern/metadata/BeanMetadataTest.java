package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class BeanMetadataTest {

	@Test
	public void testAddProperty() {
		PropertyDescriptor property = new PropertyDescriptor("name", String.class, null);
		BeanMetadata metadata = new BeanMetadata(Object.class);
		metadata.addProperty(property);
		assertEquals(1, metadata.getProperties().size());
		assertEquals(property, metadata.getProperties().get(0));
	}
}
