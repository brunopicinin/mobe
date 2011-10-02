package br.com.mobe.pattern.metadata;

import java.util.HashMap;
import java.util.Map;

import br.com.mobe.pattern.reader.MetadataReader;
import br.com.mobe.pattern.reader.MetadataReaderProvider;

public class Repository {

	private static Repository instance;

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	protected Map<Class<?>, ClassMetadata> cache;

	protected Repository() {
		cache = new HashMap<Class<?>, ClassMetadata>();
	}

	public ClassMetadata getMetadata(Class<?> clazz) {
		if (cache.containsKey(clazz)) {
			return cache.get(clazz);
		} else {
			MetadataReader reader = MetadataReaderProvider.getInstance().getReader();
			ClassMetadata metadata = reader.createMetadata(clazz);
			cache.put(clazz, metadata);
			return metadata;
		}
	}

}
