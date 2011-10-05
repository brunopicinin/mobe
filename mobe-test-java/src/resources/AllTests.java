package resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.mobe.core.metadata.ClassMetadataTest;
import br.com.mobe.core.metadata.RepositoryTest;
import br.com.mobe.core.reader.ConcreteMetadataReaderTest;
import br.com.mobe.orm.db.DatabaseBuilderTest;

@RunWith(Suite.class)
@SuiteClasses(value = { ClassMetadataTest.class, RepositoryTest.class, ConcreteMetadataReaderTest.class, DatabaseBuilderTest.class })
public class AllTests {

}
