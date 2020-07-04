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

import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.model.RecipeItem;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemplateNumberOfIngredientsFilterTest {
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
        
        InputStream template = TemplateNumberOfIngredientsFilterTest.class.getResourceAsStream("/drools/spring/templates/template-number-of-ingredients-bounds-filter.drt");
        
        List<BoundsFilterTemplateModel> data = new ArrayList<BoundsFilterTemplateModel>();
        
        data.add(new BoundsFilterTemplateModel(3, 8));
        
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        
        doTest(ksession);
    }
    

	private void doTest(KieSession ksession){
		List<Recipe> filteredRecipe = new ArrayList<Recipe>();
		// Recipe1 with 1 ingredient
		Recipe recipe1 = new Recipe();
		recipe1.setId(1L);
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		RecipeItem recipeItem1 = new RecipeItem();
		recipeItem1.setId(1L);
		recipeItem1.setIngredient(ingredient1);
		recipeItem1.setRecipe(recipe1);
		recipe1.getRecipeItems().add(recipeItem1);
		ingredient1.getRecipeItems().add(recipeItem1);
		
		// Recipe2 with 3 ingredients
		Recipe recipe2 = new Recipe();
		recipe2.setId(2L);
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);
		RecipeItem recipeItem2 = new RecipeItem();
		recipeItem2.setId(2L);
		recipeItem2.setIngredient(ingredient2);
		recipeItem2.setRecipe(recipe2);
		recipe2.getRecipeItems().add(recipeItem2);
		ingredient2.getRecipeItems().add(recipeItem2);
		
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);
		RecipeItem recipeItem3 = new RecipeItem();
		recipeItem3.setId(3L);
		recipeItem3.setIngredient(ingredient3);
		recipeItem3.setRecipe(recipe2);
		recipe2.getRecipeItems().add(recipeItem3);
		ingredient3.getRecipeItems().add(recipeItem3);
		
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4L);
		RecipeItem recipeItem4 = new RecipeItem();
		recipeItem4.setId(4L);
		recipeItem4.setIngredient(ingredient4);
		recipeItem4.setRecipe(recipe2);
		recipe2.getRecipeItems().add(recipeItem4);
		ingredient4.getRecipeItems().add(recipeItem4);
		
		// Recipe3 with 3 ingredients
		Recipe recipe3 = new Recipe();
		recipe3.setId(3L);
		Ingredient ingredient5 = new Ingredient();
		ingredient5.setId(5L);
		RecipeItem recipeItem5 = new RecipeItem();
		recipeItem5.setId(5L);
		recipeItem5.setIngredient(ingredient5);
		recipeItem5.setRecipe(recipe3);
		recipe3.getRecipeItems().add(recipeItem5);
		ingredient5.getRecipeItems().add(recipeItem5);
		
		Ingredient ingredient6 = new Ingredient();
		ingredient6.setId(6L);
		RecipeItem recipeItem6 = new RecipeItem();
		recipeItem6.setId(6L);
		recipeItem6.setIngredient(ingredient6);
		recipeItem6.setRecipe(recipe3);
		recipe3.getRecipeItems().add(recipeItem6);
		ingredient6.getRecipeItems().add(recipeItem6);
		
		Ingredient ingredient7 = new Ingredient();
		ingredient7.setId(7L);
		RecipeItem recipeItem7 = new RecipeItem();
		recipeItem7.setId(7L);
		recipeItem7.setIngredient(ingredient7);
		recipeItem7.setRecipe(recipe3);
		recipe3.getRecipeItems().add(recipeItem7);
		ingredient7.getRecipeItems().add(recipeItem7);

        
        ksession.insert(recipe1);
        ksession.insert(recipe2);
        ksession.insert(recipe3);
       
        
        ksession.setGlobal("filteredRecipe", filteredRecipe);
        ksession.fireAllRules();
        
        assertEquals(2, filteredRecipe.size());
  
    }
	

}
