package ai.plato.plato.model;

import java.util.List;

// Record representing one recipe search result.
// Contains fields returned by findRecipesByIngredients and complexSearch
public record RecipeSearchResult(
        // Common fields in both endpoints:
        Integer id,
        String image,
        String imageType,
        String title,
        // Optional fields provided only by getRecipesByIngredients:
        Integer likes,
        Integer missedIngredientCount,
        List<Ingredient> missedIngredients,
        List<Ingredient> unusedIngredients,
        Integer usedIngredientCount,
        List<Ingredient> usedIngredients
) {}

