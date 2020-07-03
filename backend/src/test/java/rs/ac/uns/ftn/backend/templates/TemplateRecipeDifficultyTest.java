package rs.ac.uns.ftn.backend.templates;

import static org.junit.Assert.assertEquals;
import static rs.ac.uns.ftn.backend.constants.DirectionConstants.*;
import static rs.ac.uns.ftn.backend.constants.RecipeConstants.*;


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

import rs.ac.uns.ftn.backend.model.Direction;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.model.enumeration.RecipeDifficultyLevels;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemplateRecipeDifficultyTest {
	
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
     * Tests customer-classification-simple.drt template by manually creating
     * the corresponding DRL using a collection of Objects as the data source.
     */
    @Test
    public void addRecipeDifficulty(){
    	InputStream template = TemplateRecipeDifficultyTest.class.getResourceAsStream("/drools/spring/templates/template-recipe-difficulty.drt");
    	List<RecipeDifficultyTemplateModel> data = new ArrayList<RecipeDifficultyTemplateModel>();
        data.add(new RecipeDifficultyTemplateModel(1, 2, RecipeDifficultyLevels.VERY_EASY));
        data.add(new RecipeDifficultyTemplateModel(3, 4, RecipeDifficultyLevels.EASY));
        data.add(new RecipeDifficultyTemplateModel(5, 6, RecipeDifficultyLevels.MODERATE));
        data.add(new RecipeDifficultyTemplateModel(7, 8, RecipeDifficultyLevels.SOMEWHAT_HARD));
        data.add(new RecipeDifficultyTemplateModel(9, 10, RecipeDifficultyLevels.HARD));
        data.add(new RecipeDifficultyTemplateModel(11, 12, RecipeDifficultyLevels.VERY_HARD));     
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);
        KieSession kieSession = createKieSessionFromDRL(drl);
        doTest(kieSession);
    }
    

	private void doTest(KieSession kieSession){
		
		// Recipe Difficulty Level VERY_EASY
		Recipe recipe_very_easy = new Recipe();
		recipe_very_easy.setId(RECIPE_ID_VERY_EASY);
		recipe_very_easy.setName(RECIPE_NAME_VERY_EASY);
		Direction direction_very_easy1 = new Direction();
		direction_very_easy1.setId(DIRECTION_ID1_VERY_EASY);
		direction_very_easy1.setDescription(DIRECTION_DESCRIPTION1_VERY_EASY);
		direction_very_easy1.setRecipe(recipe_very_easy);
		recipe_very_easy.getDirections().add(direction_very_easy1);
		
		Direction direction_very_easy2 = new Direction();
		direction_very_easy2.setId(DIRECTION_ID2_VERY_EASY);
		direction_very_easy2.setRecipe(recipe_very_easy);
		recipe_very_easy.getDirections().add(direction_very_easy2);
		
		// Recipe Difficulty Level EASY
		Recipe recipe_easy = new Recipe();
		recipe_easy.setId(RECIPE_ID_EASY);
		recipe_easy.setName(RECIPE_NAME_EASY);
		Direction direction_easy1 = new Direction();
		direction_easy1.setId(DIRECTION_ID1_EASY);
		direction_easy1.setDescription(DIRECTION_DESCRIPTION1_EASY);
		direction_easy1.setRecipe(recipe_easy);
		recipe_easy.getDirections().add(direction_easy1);
		
		Direction direction_easy2 = new Direction();
		direction_easy2.setId(DIRECTION_ID2_EASY);
		direction_easy2.setDescription(DIRECTION_DESCRIPTION2_EASY);
		direction_easy2.setRecipe(recipe_easy);
		recipe_easy.getDirections().add(direction_easy2);
		
		Direction direction_easy3 = new Direction();
		direction_easy3.setId(DIRECTION_ID3_EASY);
		direction_easy3.setDescription(DIRECTION_DESCRIPTION3_EASY);
		direction_easy3.setRecipe(recipe_easy);
		recipe_easy.getDirections().add(direction_easy3);
		
		Direction direction_easy4 = new Direction();
		direction_easy4.setId(DIRECTION_ID4_EASY);
		direction_easy4.setDescription(DIRECTION_DESCRIPTION4_EASY);
		direction_easy4.setRecipe(recipe_easy);
		recipe_easy.getDirections().add(direction_easy4);
		
		// Recipe Difficulty Level MODERATE
		Recipe recipe_moderate = new Recipe();
		recipe_moderate.setId(RECIPE_ID_MODERATE);
		recipe_moderate.setName(RECIPE_NAME_MODERATE);
		Direction direction_moderate1 = new Direction();
		direction_moderate1.setId(DIRECTION_ID1_MODERATE);
		direction_moderate1.setDescription(DIRECTION_DESCRIPTION1_MODERATE);
		direction_moderate1.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate1);
		
		Direction direction_moderate2 = new Direction();
		direction_moderate2.setId(DIRECTION_ID2_MODERATE);
		direction_moderate2.setDescription(DIRECTION_DESCRIPTION2_MODERATE);
		direction_moderate2.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate2);
		
		Direction direction_moderate3 = new Direction();
		direction_moderate3.setId(DIRECTION_ID3_MODERATE);
		direction_moderate3.setDescription(DIRECTION_DESCRIPTION3_MODERATE);
		direction_moderate3.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate3);
		
		Direction direction_moderate4 = new Direction();
		direction_moderate4.setId(DIRECTION_ID4_MODERATE);
		direction_moderate4.setDescription(DIRECTION_DESCRIPTION4_MODERATE);
		direction_moderate4.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate4);
		
		Direction direction_moderate5 = new Direction();
		direction_moderate5.setId(DIRECTION_ID5_MODERATE);
		direction_moderate5.setDescription(DIRECTION_DESCRIPTION5_MODERATE);
		direction_moderate5.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate5);
		
		Direction direction_moderate6 = new Direction();
		direction_moderate6.setId(DIRECTION_ID6_MODERATE);
		direction_moderate6.setDescription(DIRECTION_DESCRIPTION6_MODERATE);
		direction_moderate6.setRecipe(recipe_moderate);
		recipe_moderate.getDirections().add(direction_moderate6);
		
		// Recipe Difficulty Level SOMEWHAT_HARD
		Recipe recipe_somewhat_hard = new Recipe();
		recipe_somewhat_hard.setId(RECIPE_ID_SOMEWHAT_HARD);
		recipe_somewhat_hard.setName(RECIPE_NAME_SOMEWHAT_HARD);
		Direction direction_somewhat_hard1 = new Direction();
		direction_somewhat_hard1.setId(DIRECTION_ID1_SOMEWHAT_HARD);
		direction_somewhat_hard1.setDescription(DIRECTION_DESCRIPTION1_SOMEWHAT_HARD);
		direction_somewhat_hard1.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard1);
		
		Direction direction_somewhat_hard2 = new Direction();
		direction_somewhat_hard2.setId(DIRECTION_ID2_SOMEWHAT_HARD);
		direction_somewhat_hard2.setDescription(DIRECTION_DESCRIPTION2_SOMEWHAT_HARD);
		direction_somewhat_hard2.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard2);
		
		Direction direction_somewhat_hard3 = new Direction();
		direction_somewhat_hard3.setId(DIRECTION_ID3_SOMEWHAT_HARD);
		direction_somewhat_hard3.setDescription(DIRECTION_DESCRIPTION3_SOMEWHAT_HARD);
		direction_somewhat_hard3.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard3);
		
		Direction direction_somewhat_hard4 = new Direction();
		direction_somewhat_hard4.setId(DIRECTION_ID4_SOMEWHAT_HARD);
		direction_somewhat_hard4.setDescription(DIRECTION_DESCRIPTION4_SOMEWHAT_HARD);
		direction_somewhat_hard4.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard4);
		
		Direction direction_somewhat_hard5 = new Direction();
		direction_somewhat_hard5.setId(DIRECTION_ID5_SOMEWHAT_HARD);
		direction_somewhat_hard5.setDescription(DIRECTION_DESCRIPTION5_SOMEWHAT_HARD);
		direction_somewhat_hard5.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard5);
		
		Direction direction_somewhat_hard6 = new Direction();
		direction_somewhat_hard6.setId(DIRECTION_ID6_SOMEWHAT_HARD);
		direction_somewhat_hard6.setDescription(DIRECTION_DESCRIPTION6_SOMEWHAT_HARD);
		direction_somewhat_hard6.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard6);
		
		Direction direction_somewhat_hard7 = new Direction();
		direction_somewhat_hard7.setId(DIRECTION_ID7_SOMEWHAT_HARD);
		direction_somewhat_hard7.setDescription(DIRECTION_DESCRIPTION7_SOMEWHAT_HARD);
		direction_somewhat_hard7.setRecipe(recipe_somewhat_hard);
		recipe_somewhat_hard.getDirections().add(direction_somewhat_hard7);
		
		// Recipe Difficulty Level HARD
		Recipe recipe_hard = new Recipe();
		recipe_hard.setId(RECIPE_ID_HARD);
		recipe_hard.setName(RECIPE_NAME_HARD);
		Direction direction_hard1 = new Direction();
		direction_hard1.setId(DIRECTION_ID1_HARD);
		direction_hard1.setDescription(DIRECTION_DESCRIPTION1_HARD);
		direction_hard1.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard1);
		
		Direction direction_hard2 = new Direction();
		direction_hard2.setId(DIRECTION_ID2_HARD);
		direction_hard2.setDescription(DIRECTION_DESCRIPTION2_HARD);
		direction_hard2.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard2);
		
		Direction direction_hard3 = new Direction();
		direction_hard3.setId(DIRECTION_ID3_HARD);
		direction_hard3.setDescription(DIRECTION_DESCRIPTION3_HARD);
		direction_hard3.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard3);
		
		Direction direction_hard4 = new Direction();
		direction_hard4.setId(DIRECTION_ID4_HARD);
		direction_hard4.setDescription(DIRECTION_DESCRIPTION4_HARD);
		direction_hard4.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard4);
		
		Direction direction_hard5 = new Direction();
		direction_hard5.setId(DIRECTION_ID5_HARD);
		direction_hard5.setDescription(DIRECTION_DESCRIPTION5_HARD);
		direction_hard5.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard5);
		
		Direction direction_hard6 = new Direction();
		direction_hard6.setId(DIRECTION_ID6_HARD);
		direction_hard6.setDescription(DIRECTION_DESCRIPTION6_HARD);
		direction_hard6.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard6);
		
		Direction direction_hard7 = new Direction();
		direction_hard7.setId(DIRECTION_ID7_HARD);
		direction_hard7.setDescription(DIRECTION_DESCRIPTION7_HARD);
		direction_hard7.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard7);
		
		Direction direction_hard8 = new Direction();
		direction_hard8.setId(DIRECTION_ID8_HARD);
		direction_hard8.setDescription(DIRECTION_DESCRIPTION8_HARD);
		direction_hard8.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard8);
		
		Direction direction_hard9 = new Direction();
		direction_hard9.setId(DIRECTION_ID9_HARD);
		direction_hard9.setDescription(DIRECTION_DESCRIPTION9_HARD);
		direction_hard9.setRecipe(recipe_hard);
		recipe_hard.getDirections().add(direction_hard9);
		
		// Recipe Difficulty Level VERY_HARD
		Recipe recipe_very_hard = new Recipe();
		recipe_very_hard.setId(RECIPE_ID_VERY_HARD);
		recipe_very_hard.setName(RECIPE_NAME_VERY_HARD);
		Direction direction_very_hard1 = new Direction();
		direction_very_hard1.setId(DIRECTION_ID1_VERY_HARD);
		direction_very_hard1.setDescription(DIRECTION_DESCRIPTION1_VERY_HARD);
		direction_very_hard1.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard1);
		
		Direction direction_very_hard2 = new Direction();
		direction_very_hard2.setId(DIRECTION_ID2_VERY_HARD);
		direction_very_hard2.setDescription(DIRECTION_DESCRIPTION2_VERY_HARD);
		direction_very_hard2.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard2);
		
		Direction direction_very_hard3 = new Direction();
		direction_very_hard3.setId(DIRECTION_ID3_VERY_HARD);
		direction_very_hard3.setDescription(DIRECTION_DESCRIPTION3_VERY_HARD);
		direction_very_hard3.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard3);
		
		Direction direction_very_hard4 = new Direction();
		direction_very_hard4.setId(DIRECTION_ID4_VERY_HARD);
		direction_very_hard4.setDescription(DIRECTION_DESCRIPTION4_VERY_HARD);
		direction_very_hard4.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard4);
		
		Direction direction_very_hard5 = new Direction();
		direction_very_hard5.setId(DIRECTION_ID5_VERY_HARD);
		direction_very_hard5.setDescription(DIRECTION_DESCRIPTION5_VERY_HARD);
		direction_very_hard5.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard5);
		
		Direction direction_very_hard6 = new Direction();
		direction_very_hard6.setId(DIRECTION_ID6_VERY_HARD);
		direction_very_hard6.setDescription(DIRECTION_DESCRIPTION6_VERY_HARD);
		direction_very_hard6.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard6);
		
		Direction direction_very_hard7 = new Direction();
		direction_very_hard7.setId(DIRECTION_ID7_VERY_HARD);
		direction_very_hard7.setDescription(DIRECTION_DESCRIPTION7_VERY_HARD);
		direction_very_hard7.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard7);
		
		Direction direction_very_hard8 = new Direction();
		direction_very_hard8.setId(DIRECTION_ID8_VERY_HARD);
		direction_very_hard8.setDescription(DIRECTION_DESCRIPTION8_VERY_HARD);
		direction_very_hard8.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard8);
		
		Direction direction_very_hard9 = new Direction();
		direction_very_hard9.setId(DIRECTION_ID9_VERY_HARD);
		direction_very_hard9.setDescription(DIRECTION_DESCRIPTION9_VERY_HARD);
		direction_very_hard9.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard9);
		
		Direction direction_very_hard10 = new Direction();
		direction_very_hard10.setId(DIRECTION_ID10_VERY_HARD);
		direction_very_hard10.setDescription(DIRECTION_DESCRIPTION10_VERY_HARD);
		direction_very_hard10.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard10);
		
		Direction direction_very_hard11 = new Direction();
		direction_very_hard11.setId(DIRECTION_ID11_VERY_HARD);
		direction_very_hard11.setDescription(DIRECTION_DESCRIPTION11_VERY_HARD);
		direction_very_hard11.setRecipe(recipe_very_hard);
		recipe_very_hard.getDirections().add(direction_very_hard11);
		
		        
		kieSession.insert(recipe_very_easy);
        kieSession.insert(recipe_easy);
        kieSession.insert(recipe_moderate);
        kieSession.insert(recipe_somewhat_hard);
        kieSession.insert(recipe_hard);
        kieSession.insert(recipe_very_hard);
       
        
        kieSession.fireAllRules();
        
        assertEquals(recipe_very_easy.getDifficulty(), RecipeDifficultyLevels.VERY_EASY);
        assertEquals(recipe_easy.getDifficulty(), RecipeDifficultyLevels.EASY);
        assertEquals(recipe_moderate.getDifficulty(), RecipeDifficultyLevels.MODERATE);
        assertEquals(recipe_somewhat_hard.getDifficulty(), RecipeDifficultyLevels.SOMEWHAT_HARD);
        assertEquals(recipe_hard.getDifficulty(), RecipeDifficultyLevels.HARD);
        assertEquals(recipe_very_hard.getDifficulty(), RecipeDifficultyLevels.VERY_HARD);
        
       
    }

}
