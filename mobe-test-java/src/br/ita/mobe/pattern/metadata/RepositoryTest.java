package br.ita.mobe.pattern.metadata;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import pojo.BeanPojo;

public class RepositoryTest extends Repository {

	@Test
	public void testGetMetadata() {
		assertEquals(0, cache.size()); // initial condition

		Object bean1 = BeanPojo.classAnn();
		BeanMetadata mData1 = getMetadata(bean1.getClass());
		assertEquals(1, cache.size());

		Object bean2 = BeanPojo.methodAnn();
		getMetadata(bean2.getClass());
		assertEquals(2, cache.size());

		BeanMetadata mData2 = getMetadata(bean1.getClass());
		assertEquals(2, cache.size());
		assertEquals(mData1, mData2);
	}
}
