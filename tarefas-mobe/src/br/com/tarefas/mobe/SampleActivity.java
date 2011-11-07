package br.com.tarefas.mobe;

import android.app.Activity;
import br.com.mobe.core.reader.MetadataReaderProvider;
import br.com.mobe.view.ViewController;

public class SampleActivity extends Activity {

	public void execute() {
		// instantiate new reader
		XmlMetadataReader reader = new XmlMetadataReader();

		// set reader in the provider
		MetadataReaderProvider provider = MetadataReaderProvider.getInstance();
		provider.setReader(reader);

		// controller will get reader from provider
		ViewController controller = new ViewController(this);
		// execute controller
		// ...
	}

}
