package br.com.mobe.pattern.reader;

import br.com.mobe.pattern.metadata.ClassMetadata;

public interface MetadataReader {

	public ClassMetadata createMetadata(Class<?> clazz);

}
