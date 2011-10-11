package br.com.mobe.core.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import pojo.MethodAnnotation;
import pojo.core.NoFieldsBean;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Repository;

public class RepositoryTest extends Repository {

	@Test
	public void testMetadataCache() {
		assertEquals(0, cache.size()); // initial condition

		ClassMetadata mData1 = getMetadata(NoFieldsBean.class);
		assertEquals(1, cache.size());

		getMetadata(MethodAnnotation.class);
		assertEquals(2, cache.size());

		ClassMetadata mData2 = getMetadata(NoFieldsBean.class);
		assertEquals(2, cache.size());
		assertEquals(mData1, mData2);
	}
}
