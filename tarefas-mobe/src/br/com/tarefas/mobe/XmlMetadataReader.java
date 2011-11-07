package br.com.tarefas.mobe;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.reader.MetadataReader;

public class XmlMetadataReader implements MetadataReader {

	@Override
	public ClassMetadata createMetadata(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			throw new IllegalMetadataException(clazz, "@Entity annotation not present in class.");
		}
		ClassMetadata metadata = new ClassMetadata(clazz);

		// parse XML file to get properties
		// ...

		// insert properties in metadata container
		// ...

		return metadata;
	}

}
