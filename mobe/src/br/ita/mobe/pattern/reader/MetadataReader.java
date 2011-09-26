package br.ita.mobe.pattern.reader;

import br.ita.mobe.pattern.metadata.ClassMetadata;

public interface MetadataReader {

	public ClassMetadata createMetadata(Class<?> clazz);

}
