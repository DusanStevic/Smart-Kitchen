package rs.ac.uns.ftn.backend.cep;




import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.backend.events.LoginEvent;
import static rs.ac.uns.ftn.backend.constants.UserConstants.USER_ID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CepLoginEventTest {
	
	@Test
	public void moreThan3FailedLoginAttemptsInOneMinuteFromOneClientTest() {
		
    	KieServices kieServices = KieServices.Factory.get();
    	KieContainer kieContainer = kieServices.newKieClasspathContainer();
		KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
		kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
		KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);
		KieSession kieSession = kieBase.newKieSession();
		
	    
	    LoginEvent LoginEvent1 = new LoginEvent(new Date(), USER_ID);
	    LoginEvent LoginEvent2 = new LoginEvent(new Date(), USER_ID);
	    LoginEvent LoginEvent3 = new LoginEvent(new Date(), USER_ID);
	    LoginEvent LoginEvent4 = new LoginEvent(new Date(), USER_ID);
	
	        
	    kieSession.insert(LoginEvent1);
	    kieSession.insert(LoginEvent2);
	    kieSession.insert(LoginEvent3);
	    long ruleFireCount = kieSession.fireAllRules();
	    assertEquals(0, ruleFireCount);
	    kieSession.insert(LoginEvent4);    
	    ruleFireCount = kieSession.fireAllRules();
	    assertEquals(1, ruleFireCount);
	}

}
