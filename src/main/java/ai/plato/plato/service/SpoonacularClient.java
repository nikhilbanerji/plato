package ai.plato.plato.service;

import ai.plato.plato.model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

public interface SpoonacularClient {

    // Get recipes by complex search
    @GetExchange("/recipes/complexSearch")
    ComplexSearchResult getRecipesByComplexSearch(@RequestParam Map<String, String> params);

    // Get recipes by ingredients
    @GetExchange("/recipes/findByIngredients")
    List<RecipeSearchResult> getRecipesByIngredients(@RequestParam Map<String, String> params);

    // Get random recipes
    @GetExchange("/recipes/random")
    RecipeInformations getRandomRecipes(@RequestParam Map<String, String> params);

    // Get recipe information by ID
    @GetExchange("/recipes/{id}/information")
    RecipeInformation getRecipeInformation(@PathVariable Integer id);

    // Get analyzed recipe instructions by ID
    @GetExchange("/recipes/{id}/analyzedInstructions")
    List<AnalyzedInstructions> getAnalyzedRecipeInstructions(@PathVariable Integer id);

    // Get recipe summary by ID
    @GetExchange("/recipes/{id}/summary")
    RecipeInformation getRecipeSummary(@PathVariable Integer id);
}
