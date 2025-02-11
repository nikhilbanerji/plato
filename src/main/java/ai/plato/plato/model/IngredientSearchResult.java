package ai.plato.plato.model;

import java.util.List;

public record IngredientSearchResult(
        Integer offset,
        Integer number,
        List<Ingredient> results,
        Integer totalResults
) {
}
