package br.com.mobe.core.reader;

public class MetadataReaderProvider {

	private static MetadataReaderProvider instance;

	public static MetadataReaderProvider getInstance() {
		if (instance == null) {
			instance = new MetadataReaderProvider();
		}
		return instance;
	}

	private MetadataReader reader;

	private MetadataReaderProvider() {
		// default implementation
		reader = new AnnotationMetadataReader();
	}

	public MetadataReader getReader() {
		return reader;
	}

	public void setReader(MetadataReader reader) {
		this.reader = reader;
	}

}
