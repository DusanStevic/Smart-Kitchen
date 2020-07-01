package rs.ac.uns.ftn.backend.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.model.Item;



@Service
public class SampleAppService {
	@Autowired
	private DroolsService droolsService;

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	
	@Autowired 
	public SampleAppService(KieContainer kieContainer) {
	  log.info("Initialising a new example session."); 
	  this.kieContainer = kieContainer; 
	}
	 

	public Item getClassifiedItem(Item i) {
		KieSession kieSession = kieContainer.newKieSession();
		//KieSession kieSession = droolsService.getKieSession();
		kieSession.insert(i);
		kieSession.fireAllRules();
		kieSession.dispose();
		return i;
	}
}
