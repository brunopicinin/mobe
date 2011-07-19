package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import pojo.ClassAnnotation;
import pojo.MethodAnnotation;

public class RepositoryTest extends Repository {

	@Test
	public void testMetadataCache() {
		assertEquals(0, cache.size()); // initial condition

		Object bean1 = new ClassAnnotation();
		BeanMetadata mData1 = getMetadata(bean1);
		assertEquals(1, cache.size());

		Object bean2 = new MethodAnnotation();
		getMetadata(bean2);
		assertEquals(2, cache.size());

		BeanMetadata mData2 = getMetadata(bean1);
		assertEquals(2, cache.size());
		assertEquals(mData1, mData2);
	}
}
