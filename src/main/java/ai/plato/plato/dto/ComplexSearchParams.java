package ai.plato.plato.dto;

import ai.plato.plato.model.Cuisine;
import ai.plato.plato.model.Intolerance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Set;

/**
 * DTO representing all accepted query parameters for the complexSearch endpoint.
 * Only the "query" field is required.
 */
public record ComplexSearchParams(
        @NotBlank(message = "Query is required") String query,// The natural language recipe search query (required).
        Set<Cuisine> cuisine,                 // Optional: Cuisine(s) to include (comma-separated, OR).
        Set<Cuisine> excludeCuisine,          // Optional: Cuisine(s) to exclude (comma-separated, AND).
        String diet,                    // Optional: Diet(s) for which the recipes must be suitable.
        Set<Intolerance> intolerances,            // Optional: Comma-separated list of intolerances to avoid.
        String equipment,               // Optional: Required equipment (comma-separated, OR).
        String includeIngredients,      // Optional: Ingredients that must be included.
        String excludeIngredients,      // Optional: Ingredients that must be excluded.
        String type,                    // Optional: The type of recipe (e.g., main course, dessert).
        Boolean instructionsRequired,   // Optional: Whether recipes must have instructions.
        Boolean fillIngredients,        // Optional: Include detailed ingredient information.
        Boolean addRecipeInformation,   // Optional: Include additional recipe information.
        Boolean addRecipeInstructions,  // Optional: Include analyzed instructions.
        Boolean addRecipeNutrition,     // Optional: Include nutritional information.
        String author,                  // Optional: Filter recipes by the author's username.
        String tags,                    // Optional: User-defined tags that must match.
        Integer recipeBoxId,            // Optional: Recipe box ID to limit the search.
        String titleMatch,              // Optional: Text that must appear in the recipe title.
        Integer maxReadyTime,           // Optional: Maximum total time (in minutes) to prepare and cook.
        Integer minServings,            // Optional: Minimum number of servings.
        Integer maxServings,            // Optional: Maximum number of servings.
        Boolean ignorePantry,           // Optional: Whether to ignore common pantry items (e.g., water, salt).
        String sort,                    // Optional: Criteria to sort recipes (e.g., calories, popularity).
        String sortDirection,           // Optional: Sorting direction ('asc' or 'desc').
        Integer offset,                 // Optional: Number of results to skip (for pagination).
        @Positive
        Integer number                  // Optional: Maximum number of results to return.
) {}
