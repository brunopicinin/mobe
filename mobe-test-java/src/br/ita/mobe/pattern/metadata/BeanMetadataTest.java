package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class BeanMetadataTest {

	@Test
	public void testAddProperty() {
		PropertyDescriptor property = new PropertyDescriptor("name", String.class);
		BeanMetadata metadata = new BeanMetadata();
		metadata.addProperty(property);
		assertEquals(1, metadata.getProperties().size());
		assertEquals(property, metadata.getProperties().get(0));
	}
}
