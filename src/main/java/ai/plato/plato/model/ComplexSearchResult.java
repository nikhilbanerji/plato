package ai.plato.plato.model;

import java.util.List;

/**
 * Record representing the response from the Spoonacular complexSearch endpoint.
 */
public record ComplexSearchResult(
        int offset,
        int number,
        List<RecipeSearchResult> results,
        int totalResults
) {}
