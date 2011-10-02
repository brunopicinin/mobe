package br.com.mobe.core.reader;

import br.com.mobe.core.metadata.ClassMetadata;

public interface MetadataReader {

	public ClassMetadata createMetadata(Class<?> clazz);

}
