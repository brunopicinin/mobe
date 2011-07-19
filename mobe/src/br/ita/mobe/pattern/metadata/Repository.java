package br.ita.mobe.pattern.metadata;

import java.util.HashMap;
import java.util.Map;

import br.ita.mobe.pattern.reader.MetadataReader;
import br.ita.mobe.pattern.reader.MetadataReaderProvider;

public class Repository {

	private static Repository instance;

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	protected Map<Class<?>, BeanMetadata> cache;

	protected Repository() {
		cache = new HashMap<Class<?>, BeanMetadata>();
	}

	public BeanMetadata getMetadata(Object bean) {
		Class<?> clazz = bean.getClass();
		if (cache.containsKey(clazz)) {
			return cache.get(clazz);
		} else {
			MetadataReader reader = MetadataReaderProvider.getInstance().getReader();
			BeanMetadata metadata = reader.createMetadata(bean);
			cache.put(clazz, metadata);
			return metadata;
		}
	}

}
