package rs.ac.uns.ftn.backend.templates;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.backend.model.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemplatePreparationTimeFilterTest {
	private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
	
	/**
     * Tests template-preparation-time-bounds-filter.drt template by manually creating
     * the corresponding DRL using a collection of Objects as the data source.
     */
	@Test
    public void filterPreparationTimeBounds(){
        
        InputStream template = TemplatePreparationTimeFilterTest.class.getResourceAsStream("/drools/spring/templates/template-preparation-time-bounds-filter.drt");
        
        List<BoundsFilterTemplateModel> data = new ArrayList<BoundsFilterTemplateModel>();
        
        data.add(new BoundsFilterTemplateModel(18, 21));
        data.add(new BoundsFilterTemplateModel(22, 30));
        data.add(new BoundsFilterTemplateModel(31, 40));
        data.add(new BoundsFilterTemplateModel(41, 150));
        
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        
        doTest(ksession);
    }
    

	private void doTest(KieSession ksession){
		List<Recipe> filteredRecipe = new ArrayList<Recipe>();
		Recipe recipe1 = new Recipe();
		recipe1.setId(1L);
		recipe1.setPreparationTime(100);
		
		Recipe recipe2 = new Recipe();
		recipe2.setId(2L);
		recipe2.setPreparationTime(150);
		
		Recipe recipe3 = new Recipe();
		recipe3.setId(3L);
		recipe3.setPreparationTime(120);
		
		Recipe recipe4 = new Recipe();
		recipe4.setId(4L);
		recipe4.setPreparationTime(125);

        
        ksession.insert(recipe1);
        ksession.insert(recipe2);
        ksession.insert(recipe3);
        ksession.insert(recipe4);
        
        ksession.setGlobal("filteredRecipe", filteredRecipe);
        ksession.fireAllRules();
        
        assertEquals(4, filteredRecipe.size());
  
    }
	

}
