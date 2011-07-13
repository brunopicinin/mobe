package br.ita.mobe.pattern.metadata;

import java.util.HashMap;
import java.util.Map;

public class Repository {

	private static Repository instance;

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	private Map<Class<?>, BeanMetadata> cache;

	private Repository() {
		cache = new HashMap<Class<?>, BeanMetadata>();
	}

	public BeanMetadata getMetadata(Class<?> clazz) {
		if (cache.containsKey(clazz)) {
			return cache.get(clazz);
		} else {
			// BeanMetadata metadata = reader.createMetadata(clazz);
			// cache.put(clazz, metadata);
			// return metadata;
			return null;
		}
	}

}
