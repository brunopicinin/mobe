package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BeanMetadataTest {

	private BeanMetadata metadata;

	@Before
	public void setUp() throws Exception {
		metadata = new BeanMetadata();
	}

	@Test
	public void testGetProperties() {
		assertNotNull(metadata.getProperties());
	}

	@Test
	public void testAddProperty() {
		PropertyDescriptor property = new PropertyDescriptor("name", String.class);
		metadata.addProperty(property);
	}

}
