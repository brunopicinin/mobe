package resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ita.mobe.pattern.metadata.BeanMetadataTest;
import br.ita.mobe.pattern.metadata.RepositoryTest;
import br.ita.mobe.pattern.reader.ConcreteMetadataReaderTest;

@RunWith(Suite.class)
@SuiteClasses(value = { BeanMetadataTest.class, RepositoryTest.class, ConcreteMetadataReaderTest.class })
public class AllTests {

}
