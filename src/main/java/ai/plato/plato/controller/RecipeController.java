package ai.plato.plato.controller;

import ai.plato.plato.dto.ComplexSearchParams;
import ai.plato.plato.model.*;
import ai.plato.plato.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plato")
public class RecipeController {
    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Complex search for recipes
    @GetMapping("/recipes/complexSearch")
    ComplexSearchResult getRecipesByComplexSearch(@Valid @ModelAttribute ComplexSearchParams complexSearchParams) {
        log.info("Controller: Request to fetch recipes by complex search");
        return recipeService.getRecipesByComplexSearch(complexSearchParams);
    }

    // Search for recipes by ingredients
    @GetMapping("/recipes/findByIngredients")
    List<RecipeSearchResult> getRecipesByIngredients (
            @RequestParam(name = "ingredients", required = true) @NotBlank(message = "Ingredients required") String ingredients,
            @RequestParam(name = "number", required = false, defaultValue = "10") @Range(min = 1, max = 100) Integer number,
            @RequestParam(name = "ranking", required = false) @Range(min = 1, max = 2) Integer ranking,
            @RequestParam(name = "ignorePantry", required = false) Boolean ignorePantry
    ) {
        log.info("Controller: Request to fetch recipes by ingredients {}", ingredients);
        return recipeService.getRecipesByIngredients(ingredients, number, ranking, ignorePantry);
    }

    // Get random recipes
    @GetMapping("/recipes/random")
    RecipeInformations getRandomRecipes(
            @RequestParam(name = "includeNutrition", required = false) Boolean includeNutrition,
            @RequestParam(name = "includeTags", required = false) String includeTags,
            @RequestParam(name = "excludeTags", required = false) String excludeTags,
            @RequestParam(name = "number", required = false) @Range(min = 1, max = 100) Integer number
    ) {
        log.info("Controller: Request to fetch random recipes");
        return recipeService.getRandomRecipes(includeNutrition, includeTags, excludeTags, number);
    }

    // Get recipe information by ID
    @GetMapping("/recipes/{id}/information")
    RecipeInformation getRecipeInformation(@PathVariable @Positive(message = "Recipe ID must be positive") Integer id) {
        log.info("Controller: Request to fetch recipe information for id {}", id);
        return recipeService.getRecipeInformation(id);
    }

    // Get analyzed recipe instructions by ID
    @GetMapping("/recipes/{id}/analyzedInstructions")
    List<AnalyzedInstructions> getAnalyzedRecipeInstructions(@PathVariable @Positive(message = "Recipe ID must be positive") Integer id) {
        log.info("Controller: Request to fetch recipe instructions for id {}", id);
        return recipeService.getAnalyzedRecipeInstructions(id);
    }

    // Get recipe summary by ID
    @GetMapping("/recipes/{id}/summary")
    RecipeInformation getRecipeSummary(@PathVariable @Positive (message = "Recipe ID must be positive") Integer id) {
        log.info("Controller: Request to fetch recipe summary for id {}", id);
        return recipeService.getRecipeSummary(id);
    }
}
