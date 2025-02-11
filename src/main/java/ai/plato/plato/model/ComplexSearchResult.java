package ai.plato.plato.model;

import java.util.List;

/**
 * Record representing the response from the Spoonacular complexSearch endpoint.
 */
public record ComplexSearchResult(
        Integer offset,
        Integer number,
        List<RecipeSearchResult> results,
        Integer totalResults
) {}
