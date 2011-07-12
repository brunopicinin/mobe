package br.ita.mobe.pattern.reader;

import br.ita.mobe.pattern.metadata.BeanMetadata;

public interface MetadataReader {

	public BeanMetadata createMetadata(Class<?> clazz);

}
