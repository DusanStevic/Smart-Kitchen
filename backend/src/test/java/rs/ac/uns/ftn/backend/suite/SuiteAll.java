package rs.ac.uns.ftn.backend.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import rs.ac.uns.ftn.backend.cep.CepSuite;
import rs.ac.uns.ftn.backend.templates.TemplateSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CepSuite.class,
	TemplateSuite.class
})
public class SuiteAll {

}
