package ai.plato.plato.service;

import ai.plato.plato.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);
    private final SpoonacularClient spoonacularClient;

    public RecipeService(SpoonacularClient spoonacularClient) {
        this.spoonacularClient = spoonacularClient;
    }

    // Get recipes by complex search
    public ComplexSearchResult getRecipesByComplexSearch(ComplexSearchParams params) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("query", params.query());

        if (params.cuisine() != null && !params.cuisine().trim().isEmpty()) {
            queryParams.put("cuisine", params.cuisine());
        }
        if (params.excludeCuisine() != null && !params.excludeCuisine().trim().isEmpty()) {
            queryParams.put("excludeCuisine", params.excludeCuisine());
        }
        if (params.diet() != null && !params.diet().trim().isEmpty()) {
            queryParams.put("diet", params.diet());
        }
        if (params.intolerances() != null && !params.intolerances().trim().isEmpty()) {
            queryParams.put("intolerances", params.intolerances());
        }
        if (params.equipment() != null && !params.equipment().trim().isEmpty()) {
            queryParams.put("equipment", params.equipment());
        }
        if (params.includeIngredients() != null && !params.includeIngredients().trim().isEmpty()) {
            queryParams.put("includeIngredients", params.includeIngredients());
        }
        if (params.excludeIngredients() != null && !params.excludeIngredients().trim().isEmpty()) {
            queryParams.put("excludeIngredients", params.excludeIngredients());
        }
        if (params.type() != null && !params.type().trim().isEmpty()) {
            queryParams.put("type", params.type());
        }
        if (params.instructionsRequired() != null) {
            queryParams.put("instructionsRequired", params.instructionsRequired().toString());
        }
        if (params.fillIngredients() != null) {
            queryParams.put("fillIngredients", params.fillIngredients().toString());
        }
        if (params.addRecipeInformation() != null) {
            queryParams.put("addRecipeInformation", params.addRecipeInformation().toString());
        }
        if (params.addRecipeInstructions() != null) {
            queryParams.put("addRecipeInstructions", params.addRecipeInstructions().toString());
        }
        if (params.addRecipeNutrition() != null) {
            queryParams.put("addRecipeNutrition", params.addRecipeNutrition().toString());
        }
        if (params.author() != null && !params.author().trim().isEmpty()) {
            queryParams.put("author", params.author());
        }
        if (params.tags() != null && !params.tags().trim().isEmpty()) {
            queryParams.put("tags", params.tags());
        }
        if (params.recipeBoxId() != null && params.recipeBoxId() != 0) {
            queryParams.put("recipeBoxId", params.recipeBoxId().toString());
        }
        if (params.titleMatch() != null && !params.titleMatch().trim().isEmpty()) {
            queryParams.put("titleMatch", params.titleMatch());
        }
        if (params.maxReadyTime() != null && params.maxReadyTime() != 0) {
            queryParams.put("maxReadyTime", params.maxReadyTime().toString());
        }
        if (params.minServings() != null && params.minServings() != 0) {
            queryParams.put("minServings", params.minServings().toString());
        }
        if (params.maxServings() != null && params.maxServings() != 0) {
            queryParams.put("maxServings", params.maxServings().toString());
        }
        if (params.ignorePantry() != null) {
            queryParams.put("ignorePantry", params.ignorePantry().toString());
        }
        if (params.sort() != null && !params.sort().trim().isEmpty()) {
            queryParams.put("sort", params.sort());
        }
        if (params.sortDirection() != null && !params.sortDirection().trim().isEmpty()) {
            queryParams.put("sortDirection", params.sortDirection());
        }
        if (params.offset() != null && params.offset() != 0) {
            queryParams.put("offset", params.offset().toString());
        }
        if (params.number() != null && params.number() != 0) {
            queryParams.put("number", params.number().toString());
        }

        try {
            log.info("Fetching complex search recipes with parameters: " + queryParams);
            ComplexSearchResult response = spoonacularClient.getRecipesByComplexSearch(queryParams);

            if (response == null || response.results() == null || response.results().isEmpty()) {
                throw new RecipeNotFoundException("No recipes found with parameters: " + queryParams);
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecipeNotFoundException("Recipes not found with parameters: " + queryParams, e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for complex search with parameters: " + queryParams, e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }

    // Search for recipes by ingredients
    public List<RecipeSearchResult> getRecipesByIngredients(
            String ingredients,
            Integer number,
            Integer ranking,
            Boolean ignorePantry
    ) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("ingredients", ingredients); // required
        queryParams.put("number", number.toString()); // defaults to 10 if not entered

        if (ranking != null) {
            queryParams.put("ranking", ranking.toString());
        }
        if (ignorePantry != null) {
            queryParams.put("ignorePantry", ignorePantry.toString());
        }

        try {
            log.info("Fetching recipes by entered ingredients: " + ingredients);
            List<RecipeSearchResult> searchResults = spoonacularClient.getRecipesByIngredients(queryParams);
            if (searchResults == null || searchResults.isEmpty()) {
                throw new RecipeNotFoundException("Recipes with ingredients " + ingredients + " not found.");
            }
            return searchResults;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecipeNotFoundException("Recipe with ingredients " + ingredients + " not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for recipe information", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }

    // Get random recipes
    public RecipeInformations getRandomRecipes(
            Boolean includeNutrition,
            String includeTags,
            String excludeTags,
            Integer number
    ) {
        Map<String, String> queryParams = new HashMap<>();

        if (includeNutrition != null) {
            queryParams.put("ranking", includeNutrition.toString());
        }
        if (includeTags != null) {
            queryParams.put("includeTags", includeTags);
        }
        if (excludeTags != null) {
            queryParams.put("excludeTags", excludeTags);
        }
        if (number != null) {
            queryParams.put("number", number.toString());
        }

        try {
            log.info("Fetching {} random recipes", number);
            RecipeInformations recipeInformations = spoonacularClient.getRandomRecipes(queryParams);
            if (recipeInformations == null || recipeInformations.recipes() == null ||  recipeInformations.recipes().isEmpty()) {
                throw new RecipeNotFoundException("Recipes not found.");
            }
            return recipeInformations;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecipeNotFoundException("Recipes not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for random recipes", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }

    // Get recipe information by ID
    public RecipeInformation getRecipeInformation(Integer id) {
        try {
            log.info("Fetching recipe information for id = {}", id);
            RecipeInformation recipe = spoonacularClient.getRecipeInformation(id);
            if (recipe == null) {
                throw new RecipeNotFoundException("Recipe with id " + id + " not found.");
            }
            return recipe;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecipeNotFoundException("Recipe with id " + id + " not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for recipe information", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }

    // Get recipe summary by ID
    public RecipeInformation getRecipeSummary(Integer id) {
        try {
            log.info("Fetching recipe summary for id = {}", id);
            RecipeInformation recipe = spoonacularClient.getRecipeSummary(id);
            if (recipe == null) {
                throw new RecipeNotFoundException("Recipe with id " + id + " not found.");
            }
            return recipe;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecipeNotFoundException("Recipe with id " + id + " not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for recipe information", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }
}
