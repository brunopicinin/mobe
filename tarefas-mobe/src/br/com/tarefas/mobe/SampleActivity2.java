package br.com.tarefas.mobe;

import java.util.List;

import android.app.Activity;
import br.com.mobe.core.reader.AnnotationMetadataReader;
import br.com.mobe.core.reader.MetadataReaderProvider;
import br.com.mobe.view.ViewController;

public class SampleActivity2 extends Activity {

	public void execute() {
		// instantiate new processor
		ListProcessor processor = new ListProcessor();

		// add processor in the reader
		AnnotationMetadataReader reader = (AnnotationMetadataReader) MetadataReaderProvider.getInstance().getReader();
		reader.addNewProcessor(List.class, processor);

		// controller will get processor if it finds any List
		ViewController controller = new ViewController(this);
		// execute controller
		// ...
	}

}
